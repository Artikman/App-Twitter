package com.example.twitter.room.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "tweets",
    foreignKeys = [ForeignKey(entity = User::class,
        parentColumns = ["id"],
        childColumns = ["user_id"])])
    data class Tweet (
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "created_at")
    val createdAt: String,
    @ColumnInfo(name = "text")
    val text: String,
    @ColumnInfo(name = "retweet_count")
    val retweetCount: Int,
    @ColumnInfo(name = "retweeted")
    val retweeted: Boolean,
    @ColumnInfo(name = "favorite_count")
    val favoriteCount: Int,
    @ColumnInfo(name = "favourited")
    val favourited: Boolean,
    @ColumnInfo(name = "user_id")
    val userId: Long
)