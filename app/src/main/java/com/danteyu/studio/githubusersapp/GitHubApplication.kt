package com.danteyu.studio.githubusersapp

import android.app.Application
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.util.ServiceLocator
import kotlin.properties.Delegates

/**
 * Created by George Yu on 2020/4/26.
 *
 * An application that lazily provides a repository. Note that this Service Locator pattern is
 * used to simplify Dependency Injection framework.
 */
class GitHubApplication : Application() {

    val gitHubRepository: GitHubRepository
        get() = ServiceLocator.provideRepository()

    companion object {
        var instance: GitHubApplication by Delegates.notNull()
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}