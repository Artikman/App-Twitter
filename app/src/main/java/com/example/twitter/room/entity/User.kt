package com.example.twitter.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "screenName")
    val screenName: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "url_user")
    val url_user: String,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "friends_count")
    val friendsCount: Int,
    @ColumnInfo(name = "followers_count")
    val followersCount: Int,
    @ColumnInfo(name = "profile_image_url_https")
    val profileImageUrlHttps: String,
    @ColumnInfo(name = "profile_banner_url")
    val profileBannerUrl: String
)