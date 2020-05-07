package com.example.twitter.di

import com.example.twitter.storage.Repository
import com.example.twitter.storage.TemporaryDataStorage
import dagger.Binds
import dagger.Module

@Module
abstract class StorageModule {

    @Binds
    abstract fun provideStorage(tweets: TemporaryDataStorage): Repository
}