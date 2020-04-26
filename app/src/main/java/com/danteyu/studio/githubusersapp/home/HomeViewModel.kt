package com.danteyu.studio.githubusersapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.network.LoadApiStatus
import com.danteyu.studio.githubusersapp.util.Logger

/**
 * Created by George Yu on 2020/4/26.
 */
class HomeViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {


    private val _status = MutableLiveData<LoadApiStatus>()

    val status: LiveData<LoadApiStatus>
        get() = _status

    private val _error = MutableLiveData<String>()

    val error: LiveData<String>
        get() = _error

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

    }
}