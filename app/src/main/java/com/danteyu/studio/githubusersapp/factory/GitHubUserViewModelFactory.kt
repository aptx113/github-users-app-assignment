package com.danteyu.studio.githubusersapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.detail.DetailViewModel

/**
 * Created by George Yu on 2020/4/28.
 *
 * Factory for all ViewModels which need [GitHubUser].
 */
@Suppress("UNCHECKED_CAST")
class GitHubUserViewModelFactory constructor(
    private val gitHubRepository: GitHubRepository,
    private val gitHubUser: GitHubUser
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(DetailViewModel::class.java) -> DetailViewModel(
                gitHubRepository,
                gitHubUser
            )

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

    } as T
}