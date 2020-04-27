package com.danteyu.studio.githubusersapp.network

import com.danteyu.studio.githubusersapp.BuildConfig
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by George Yu on 2020/4/26.
 */
private const val BASE_URL = "https://api.github.com/"

/**
 * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
 * full Kotlin compatibility.
 */
private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = when (BuildConfig.LOGGER_VISIABLE) {
            true -> HttpLoggingInterceptor.Level.BODY
            false -> HttpLoggingInterceptor.Level.NONE
        }
    })
    .build()

/**
 * Use the Retrofit builder to build a retrofit object using a Moshi converter with our Moshi
 * object.
 */
private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(BASE_URL)
    .client(client)
    .build()

/**
 * A public interface that exposes the [getAllGitHubUserAsync], [getSingleGitHubUserAsync] methods
 */
interface GitHubApiService {

    @GET("users")
    fun getAllGitHubUserAsync(): Deferred<List<GitHubUser>>

    @GET("users/{username}")
    fun getSingleGitHubUserAsync(@Path("username") username: String): Deferred<GitHubUser>

}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object GitHubApi {
    val retrofitService: GitHubApiService by lazy { retrofit.create(GitHubApiService::class.java) }
}