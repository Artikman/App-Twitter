package com.example.twitter.room.entity

import androidx.room.Embedded

class TweetWithUser {

    @Embedded(prefix = "tweet_")
    lateinit var tweet: Tweet

    @Embedded
    lateinit var user: User

}