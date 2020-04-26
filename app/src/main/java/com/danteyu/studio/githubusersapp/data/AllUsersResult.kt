package com.danteyu.studio.githubusersapp.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by George Yu on 2020/4/27.
 */
@Parcelize
data class AllUsersResult(
    val error: String? = null,
    val data: List<GitHubUser>? = null
) : Parcelable