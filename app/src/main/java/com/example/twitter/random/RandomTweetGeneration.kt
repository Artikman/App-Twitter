package com.example.twitter.random

import com.example.twitter.model.TweetItem
import com.example.twitter.storage.TemporaryDataStorage
import com.example.twitter.ui.timeline.adapter.AllTweetAdapter

class RandomTweetGeneration {
    companion object {

        private var flag = true
        private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

        fun addTweets() {
            if (flag) {
                for (i in 0..30) {
                    val tweet = randomTweet()
                    TemporaryDataStorage.create(tweet)
                    promotionTweets()
                }
                flag = false
            }
        }

        private fun promotionTweets() {
            if ((0..3).random() == 1) {
                val tweetAdvertisement = TweetItem(type = AllTweetAdapter.PROMOTION)
                TemporaryDataStorage.create(tweetAdvertisement)
            }
        }

        private fun randomTweet(): TweetItem {
            val name = randomText((3..10).random())
            val message = randomText((10..30).random())
            return TemporaryDataStorage.newTweet(name, message)
        }

        private fun randomText(random: Int): String {
            return (1..random)
                .map { kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
        }
    }
}