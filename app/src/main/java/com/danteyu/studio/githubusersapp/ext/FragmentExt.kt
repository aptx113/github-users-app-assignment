package com.danteyu.studio.githubusersapp.ext

import androidx.fragment.app.Fragment
import com.danteyu.studio.githubusersapp.GitHubApplication
import com.danteyu.studio.githubusersapp.factory.ViewModelFactory

/**
 * Created by George Yu on 2020/4/26.
 *
 * Extension functions for Fragment.
 */
fun Fragment.getVmFactory(): ViewModelFactory {
    val repository = (requireContext().applicationContext as GitHubApplication).gitHubRepository
    return ViewModelFactory(repository)
}