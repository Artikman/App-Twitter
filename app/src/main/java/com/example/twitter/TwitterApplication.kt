package com.example.twitter

import android.app.Application
import com.example.twitter.di.AppComponent
import com.example.twitter.di.DaggerAppComponent

class TwitterApplication: Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
}