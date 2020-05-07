package com.example.twitter.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TweetItemPayLoad(
    @SerializedName("id")
    val id: Long,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("text")
    val text: String,
    @SerializedName("retweet_count")
    val retweetCount: Int,
    @SerializedName("retweeted")
    val retweeted: Boolean,
    @SerializedName("favorite_count")
    val favoriteCount: Int,
    @SerializedName("favorited")
    val favourited: Boolean,
    @SerializedName("user")
    val user: UserPayLoad
) : Parcelable