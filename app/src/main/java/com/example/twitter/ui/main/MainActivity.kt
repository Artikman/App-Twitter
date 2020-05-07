package com.example.twitter.ui.main

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.twitter.R

import kotlinx.android.synthetic.main.activity_fragment.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val animationStart = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        mainContent.startAnimation(animationStart)
    }
}