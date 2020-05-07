package com.example.twitter.storage

import androidx.lifecycle.MutableLiveData
import com.example.twitter.model.TweetItemPayLoad
import com.example.twitter.service.TweetServiceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class TemporaryDataStorage @Inject constructor(private val twitter: TweetServiceApi) : Repository {

    override fun create(message: String): MutableLiveData<Boolean> {
        val request = MutableLiveData(false)
        twitter
            .postTimeLine(message)
            .enqueue(object : Callback<TweetItemPayLoad> {
                override fun onFailure(call: Call<TweetItemPayLoad>, t: Throwable) {
                    println("Failed to execute request")
                }

                override fun onResponse(call: Call<TweetItemPayLoad>, response: Response<TweetItemPayLoad>) {
                    val tweet = response.body()
                    println("Call result: $tweet")
                    request.value = true
                }

            })
        return request
    }

    override fun update(): MutableLiveData<List<TweetItemPayLoad>> {
        val tweetsLiveData = MutableLiveData<List<TweetItemPayLoad>>()
        twitter
            .getTimeLine()
            .enqueue(object : Callback<List<TweetItemPayLoad>> {
                override fun onFailure(call: Call<List<TweetItemPayLoad>>, t: Throwable) {
                    println("Failed to execute request")
                }

                override fun onResponse(call: Call<List<TweetItemPayLoad>>, response: Response<List<TweetItemPayLoad>>) {
                    val tweets = response.body()
                    if (tweets != null) {
                        tweetsLiveData.value = tweets
                        println("Call result: $tweets")
                    }
                }
            })
        return tweetsLiveData
    }
}