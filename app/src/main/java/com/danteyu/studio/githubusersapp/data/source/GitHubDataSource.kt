package com.danteyu.studio.githubusersapp.data.source

import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result

/**
 * Created by George Yu on 2020/4/26.
 */
interface GitHubDataSource {

    suspend fun getAllUsers():Result<List<GitHubUser>>
}