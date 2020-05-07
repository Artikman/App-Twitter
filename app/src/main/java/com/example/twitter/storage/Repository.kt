package com.example.twitter.storage

import androidx.lifecycle.LiveData
import com.example.twitter.model.TweetItemPayLoad

interface Repository {

    fun create(message: String): LiveData<Boolean>

    fun update(): LiveData<List<TweetItemPayLoad>>

}