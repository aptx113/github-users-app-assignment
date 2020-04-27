package com.danteyu.studio.githubusersapp.home

import android.graphics.Rect
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.RecyclerView
import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.data.GitHubUser
import com.danteyu.studio.githubusersapp.data.Result
import com.danteyu.studio.githubusersapp.data.source.GitHubRepository
import com.danteyu.studio.githubusersapp.network.LoadApiStatus
import com.danteyu.studio.githubusersapp.util.Logger
import com.danteyu.studio.githubusersapp.util.Util.getDimensionPixelSize
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

    // status for the loading icon of swl
    private val _refreshStatus = MutableLiveData<Boolean>()

    val refreshStatus: LiveData<Boolean>
        get() = _refreshStatus

    val decoration = object : RecyclerView.ItemDecoration() {
        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            super.getItemOffsets(outRect, view, parent, state)
            outRect.apply {
                left = getDimensionPixelSize(R.dimen.space_min)
                top = getDimensionPixelSize(R.dimen.space_min)
                right = getDimensionPixelSize(R.dimen.space_min)
                bottom = getDimensionPixelSize(R.dimen.space_min)
            }
        }
    }

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
            _refreshStatus.value = false
        }
    }

    fun refresh() {
        if (status.value != LoadApiStatus.LOADING) {
            getAllUsersResult()
        }
    }
}