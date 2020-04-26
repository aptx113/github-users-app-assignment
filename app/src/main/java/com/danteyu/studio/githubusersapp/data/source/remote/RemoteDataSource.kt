package com.danteyu.studio.githubusersapp.data.source.remote

import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.data.AllUsersResult
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result
import com.danteyu.studio.githubusersapp.data.source.GitHubDataSource
import com.danteyu.studio.githubusersapp.network.GitHubApi
import com.danteyu.studio.githubusersapp.util.Logger
import com.danteyu.studio.githubusersapp.util.Util.getString
import com.danteyu.studio.githubusersapp.util.Util.isInternetAvailable

/**
 * Created by George Yu on 2020/4/26.
 */

object RemoteDataSource : GitHubDataSource {

    override suspend fun getAllUsers(): Result<List<GitHubUser>> {
        if (!isInternetAvailable()) {
            return Result.Fail(getString(R.string.internet_not_connected))
        }
        // Get the Deferred object for our Retrofit request
        val getResultDeferred = GitHubApi.retrofitService.getAllGitHubUserAsync()
        return try {
            // this will run on a thread managed by Retrofit
            val listResult = getResultDeferred.await()

//            listResult.error?.let {
//                return Result.Fail(it)
//            }
            Result.Success(listResult)
//            listResult?.let {
//                return Result.Success(it)
//            }
//            Result.Fail(getString(R.string.you_know_nothing))

        } catch (e: Exception) {
            Logger.w("[${this::class.simpleName}] exception=${e.message}")
            Result.Error(e)
        }
    }
}