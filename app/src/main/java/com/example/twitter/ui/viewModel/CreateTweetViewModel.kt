package com.example.twitter.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.twitter.storage.Repository
import javax.inject.Inject

class CreateTweetViewModel @Inject constructor(private val tweets: Repository): ViewModel() {

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    private val _goBackToTimeLine = MutableLiveData(false)
    val goBackToTimeLine: LiveData<Boolean> = _goBackToTimeLine

    private var requestLiveData = MutableLiveData(false)

    private val observerRequest = Observer<Boolean> {
        if(it) {
            _loading.value = false
            _goBackToTimeLine.value = it
        } else {
            _loading.value = it
            _goBackToTimeLine.value = false
        }
    }

    fun createTweet(message: String) {
        requestLiveData = tweets.create(message) as MutableLiveData<Boolean>
        requestLiveData.observeForever(observerRequest)
    }

    override fun onCleared() {
        super.onCleared()
        requestLiveData.removeObserver(observerRequest)
    }
}