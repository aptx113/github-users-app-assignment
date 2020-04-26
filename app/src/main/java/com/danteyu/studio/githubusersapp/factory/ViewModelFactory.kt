package com.danteyu.studio.githubusersapp.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.danteyu.studio.githubusersapp.MainViewModel
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import java.lang.IllegalArgumentException

/**
 * Created by George Yu on 2020/4/26.
 *
 * Factory for all ViewModels.
 */
@Suppress("UNCHECKED_CAST")
class ViewModelFactory constructor(private val gitHubRepository: GitHubRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>) = with(modelClass) {
        when {
            isAssignableFrom(MainViewModel::class.java) -> MainViewModel(gitHubRepository)

            else -> throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
        }

    } as T
}