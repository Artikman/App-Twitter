package com.example.twitter.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.twitter.model.TweetItemPayLoad
import com.example.twitter.storage.Repository
import javax.inject.Inject

class HomeTweetViewModel @Inject constructor(private val tweets: Repository): ViewModel() {

    private val tweetsData: LiveData<List<TweetItemPayLoad>> by lazy {
        tweets.update()
    }

    fun getTweets(): LiveData<List<TweetItemPayLoad>> {
        return tweetsData
    }
}