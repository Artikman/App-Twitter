package com.example.twitter.service

import com.example.twitter.model.TweetItemPayLoad
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface TweetServiceApi {

    @GET("statuses/home_timeline.json?count=200")
    fun getTimeLine(): Call<List<TweetItemPayLoad>>

    @POST("statuses/update.json")
    fun postTimeLine(@Query("status") message: String): Call<TweetItemPayLoad>
}