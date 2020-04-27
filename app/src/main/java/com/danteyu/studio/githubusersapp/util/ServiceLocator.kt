package com.danteyu.studio.githubusersapp.util

import androidx.annotation.VisibleForTesting
import com.danteyu.studio.githubusersapp.data.source.DefaultGitHubRepository
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.data.source.remote.RemoteDataSource

/**
 * Created by George Yu on 2020/4/26.
 *
 * A Service Locator for the [GitHubRepository].
 */
object ServiceLocator {

    @Volatile
    var gitHubRepository: GitHubRepository? = null
        @VisibleForTesting set

    fun provideRepository(): GitHubRepository {
        synchronized(this) {
            return gitHubRepository ?: createGitHubRepository()
        }
    }

    private fun createGitHubRepository(): GitHubRepository {
        return DefaultGitHubRepository(RemoteDataSource)
    }
}