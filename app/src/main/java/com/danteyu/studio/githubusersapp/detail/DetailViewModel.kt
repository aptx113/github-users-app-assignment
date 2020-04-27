package com.danteyu.studio.githubusersapp.detail

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
 * Created by George Yu on 2020/4/28.
 */
class DetailViewModel(
    private val gitHubRepository: GitHubRepository,
    private val argument: GitHubUser
) : ViewModel() {

    private val _userDetail = MutableLiveData<GitHubUser>().apply {
        value = argument
    }

    val userDetail: LiveData<GitHubUser>
        get() = _userDetail

    private val _backToHome = MutableLiveData<Boolean>()

    val backToHome: LiveData<Boolean>
        get() = _backToHome

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

        getSingleUserResult(argument.login)
    }

    private fun getSingleUserResult(username: String) {

        viewModelScope.launch {

            _status.value = LoadApiStatus.LOADING

            val result = gitHubRepository.getSingleUser(username)

            _userDetail.value = when (result) {

                is Result.Success -> {
                    _error.value = null
                    _status.value = LoadApiStatus.DONE
                    result.data
                }
                is Result.Fail -> {
                    _error.value = result.error
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                is Result.Error -> {
                    _error.value = result.exception.toString()
                    _status.value = LoadApiStatus.ERROR
                    null
                }
                else -> {
                    _error.value = getString(R.string.you_know_nothing)
                    _status.value = LoadApiStatus.ERROR
                    null
                }
            }
        }
    }

    fun backToHome() {
        _backToHome.value = true
    }

    fun onHomeBacked() {
        _backToHome.value = null
    }
}