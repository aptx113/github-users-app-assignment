package com.danteyu.studio.githubusersapp.util

import com.danteyu.studio.githubusersapp.R
import com.danteyu.studio.githubusersapp.util.Util.getString

/**
 * Created by George Yu on 2020/4/26.
 */
enum class CurrentFragmentType(val value: String) {
    HOME(getString(R.string.user_list)),
    DETAIL(getString(R.string.user_detail))
}