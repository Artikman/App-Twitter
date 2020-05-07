package com.example.twitter.di

import android.content.Context
import com.example.twitter.ui.fragments.CreateTweetFragment
import com.example.twitter.ui.fragments.HomeTimelineFragment
import com.example.twitter.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [StorageModule::class, AppModule::class, ViewModelModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun inject(activity: MainActivity)

    fun inject(fragment: CreateTweetFragment)

    fun inject(fragment: HomeTimelineFragment)
}