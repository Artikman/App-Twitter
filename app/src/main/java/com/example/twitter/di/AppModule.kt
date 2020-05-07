package com.example.twitter.di

import com.example.twitter.service.TweetServiceApi
import com.example.twitter.service.TweetServiceApiImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideTwitter() = TweetServiceApiImpl().twitterAPI
}