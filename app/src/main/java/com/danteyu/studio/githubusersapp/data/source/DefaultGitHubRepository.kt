package com.danteyu.studio.githubusersapp.data.source

import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result

/**
 * Created by George Yu on 2020/4/26.
 */
class DefaultGitHubRepository(private val remoteDataSource: GitHubDataSource) : GitHubRepository {

    override suspend fun getAllUsers(): Result<List<GitHubUser>> {
        return remoteDataSource.getAllUsers()
    }

    override suspend fun getSingleUser(username: String): Result<GitHubUser> {
        return remoteDataSource.getSingleUser(username)
    }
}