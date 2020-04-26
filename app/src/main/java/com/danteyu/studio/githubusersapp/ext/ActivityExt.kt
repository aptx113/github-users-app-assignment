package com.danteyu.studio.githubusersapp.ext

import android.app.Activity
import com.danteyu.studio.githubusersapp.GitHubApplication
import com.danteyu.studio.githubusersapp.factory.ViewModelFactory

/**
 * Created by George Yu on 2020/4/26.
 *
 * Extension functions for Activity.
 */
fun Activity.getVmFactory(): ViewModelFactory {
    val repository = (applicationContext as GitHubApplication).gitHubRepository
    return ViewModelFactory(repository)
}
