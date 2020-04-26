package com.danteyu.studio.githubusersapp.util

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import com.danteyu.studio.githubusersapp.GitHubApplication

/**
 * Created by George Yu on 2020/4/26.
 */
object Util {

    /**
     * Determine and monitor the connectivity status
     *
     * https://developer.android.com/training/monitoring-device-state/connectivity-monitoring
     */
    fun isInternetAvailable(): Boolean {
        var result = false
        val connectivityManager =
            GitHubApplication.instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }
        return result
    }

    fun getString(resourceId: Int): String {
        return GitHubApplication.instance.getString(resourceId)
    }

    fun getColor(resourceId: Int): Int {
        return GitHubApplication.instance.getColor(resourceId)
    }

    fun getDrawable(resourceId: Int): Drawable? {
        return GitHubApplication.instance.getDrawable(resourceId)
    }

    fun getDimensionPixelSize(resourceId: Int): Int {
        return GitHubApplication.instance.resources.getDimensionPixelSize(resourceId)
    }
}