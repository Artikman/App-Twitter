package com.example.twitter.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserPayLoad(
    @SerializedName("id")
    val id: Long,
    @SerializedName("name")
    val name: String,
    @SerializedName("screen_name")
    val screenName: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("url")
    val url_user: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("friends_count")
    val friendsCount: Int,
    @SerializedName("followers_count")
    val followersCount: Int,
    @SerializedName("profile_image_url_https")
    val profileImageUrlHttps: String,
    @SerializedName("profile_banner_url")
    val profileBannerUrl: String
) : Parcelable