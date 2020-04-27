package com.danteyu.studio.githubusersapp.data

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

/**
 * Created by George Yu on 2020/4/26.
 */
@Parcelize
data class GitHubUser(
    val login: String,
    val id: Long,
    @Json(name = "node_id")
    val nodeId: String,
    @Json(name = "avatar_url")
    val avatarUrl: String,
    @Json(name = "gravatar_id")
    val gravatarId: String,
    val url: String,
    @Json(name = "html_url")
    val htmlUrl: String,
    @Json(name = "followers_url")
    val followersUrl: String,
    @Json(name = "following_url")
    val followingUrl: String,
    @Json(name = "gists_url")
    val gistsUrl: String,
    @Json(name = "starred_url")
    val starredUrl: String,
    @Json(name = "subscriptions_url")
    val subscriptionsUrl: String,
    @Json(name = "organizations_url")
    val organizationsUrl: String,
    @Json(name = "repos_url")
    val reposUrl: String,
    @Json(name = "events_url")
    val eventsUrl: String,
    @Json(name = "received_events_url")
    val receivedEventsUrl: String,
    val type: String,
    @Json(name = "site_admin")
    val siteAdmin: Boolean,
    var name: String? = null,
    var company: String? = null,
    var blog: String? = null,
    var location: String? = null,
    var email: String? = null,
    var hireable: Boolean? = null,
    var bio: String? = null,
    @Json(name = "public_repos")
    var publicRepos: Long? = null,
    @Json(name = "public_gists")
    var publicGists: Long? = null,
    var followers: Long? = null,
    var following: Long? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null

) : Parcelable