package com.example.twitter.storage

import com.example.twitter.model.TweetItem

object TemporaryDataStorage: Crud<TweetItem>  {

    var twitterCollection: MutableList<TweetItem> = mutableListOf()

    override fun create(tweetItem: TweetItem) {
        twitterCollection.add(0, tweetItem)
    }

    override fun read() : MutableList<TweetItem> {
        return twitterCollection
    }

    override fun update(tweetItem: TweetItem, index: Int) {
        if(index < twitterCollection.size) {
            twitterCollection.removeAt(index)
            twitterCollection.add(index, tweetItem)
        }
    }

    override fun delete(index: Int) {
       if(index < twitterCollection.size) {
           twitterCollection.removeAt(index)
       }
    }

    fun newTweet(name: String, message: String): TweetItem {
        return TweetItem(name = name, message = message)
    }
}