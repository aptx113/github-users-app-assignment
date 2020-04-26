package com.danteyu.studio.githubusersapp.data.source.remote

import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result
import com.danteyu.studio.githubusersapp.data.source.GitHubDataSource

/**
 * Created by George Yu on 2020/4/26.
 */

object RemoteDataSource :GitHubDataSource{

    override suspend fun getAllUsers(): Result<List<GitHubUser>> {
        TODO("Not yet implemented")
    }
}