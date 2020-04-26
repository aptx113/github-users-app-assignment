package com.danteyu.studio.githubusersapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.danteyu.studio.githubusersapp.util.CurrentFragmentType
import com.danteyu.studio.githubusersapp.util.Logger

/**
 * Created by George Yu on 2020/4/26.
 */
class MainViewModel ():ViewModel(){

    val currentFragmentType = MutableLiveData<CurrentFragmentType>()

    init {
        Logger.i("------------------------------------")
        Logger.i("[${this::class.simpleName}]${this}")
        Logger.i("------------------------------------")

    }
}