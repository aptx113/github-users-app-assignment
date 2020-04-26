package com.danteyu.studio.githubusersapp.util

import android.util.Log
import com.danteyu.studio.githubusersapp.BuildConfig

/**
 * Created by George Yu on 2020/4/26.
 */
object Logger {

    private const val TAG = "Dante-GitHub User App"

    fun v(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.v(TAG, content) }
    fun d(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.d(TAG, content) }
    fun i(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.i(TAG, content) }
    fun w(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.w(TAG, content) }
    fun e(content: String) { if (BuildConfig.LOGGER_VISIABLE) Log.e(TAG, content) }

}