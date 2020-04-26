package com.danteyu.studio.githubusersapp.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.network.LoadApiStatus
import com.danteyu.studio.githubusersapp.util.Logger
import com.danteyu.studio.githubusersapp.util.Util.getString
import kotlinx.coroutines.launch

/**
 * Created by George Yu on 2020/4/26.
 */
class HomeViewModel(private val gitHubRepository: GitHubRepository) : ViewModel() {

    private val _users = MutableLiveData<List<GitHubUser>>()

    val users: LiveData<List<GitHubUser>>
        get() = _users

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

        getAllUsersResult(true)
    }

    private fun getAllUsersResult(isInitial: Boolean = false) {

        viewModelScope.launch {

            if (isInitial) _status.value = LoadApiStatus.LOADING
            // It will return Result object after Deferred flow
            val result = gitHubRepository.getAllUsers()

            _users.value = when (result) {
                is Result.Success -> {
                    _error.value = null
                    if (isInitial) _status.value = LoadApiStatus.DONE
                    result.data
                }
                is Result.Fail -> {
                    _error.value = result.error
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = getString(R.string.you_know_nothing)
                    if (isInitial) _status.value = LoadApiStatus.ERROR
                    null
                }
            }
        }
    }

    fun refresh() {
        if (status.value != LoadApiStatus.LOADING) {
            getAllUsersResult()
        }
    }
}