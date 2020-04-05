package com.example.twitter.model

import com.example.twitter.ui.timeline.adapter.AllTweetAdapter
import java.util.*

data class TweetItem(
    val name: String? = "name",
    val idName: String = "@bonczek",
    val date: Date = Date(),
    val message: String? = "message",
    val comments: Int = 1,
    val lock: Int = 2,
    val likes: Int = 8,
    val type: Int = AllTweetAdapter.TWEET
)