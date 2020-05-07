package com.example.twitter.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.twitter.R
import com.example.twitter.TwitterApplication
import com.example.twitter.ui.viewModel.CreateTweetViewModel

import kotlinx.android.synthetic.main.fragment_create.*
import javax.inject.Inject

class CreateTweetFragment : Fragment(R.layout.fragment_create) {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var createTweetViewModel: CreateTweetViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        createTweetViewModel = ViewModelProvider(this, viewModelProviderFactory).get(CreateTweetViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)
        editTextMessage.requestFocus()
        val irm = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        irm.showSoftInput(editTextMessage, InputMethodManager.SHOW_IMPLICIT)

        back.setOnClickListener {
            navigateToTimeline()
        }

        sendTweetButton.setOnClickListener {
            navigateToTimeline()
        }

        subscribeViewModel()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as TwitterApplication).appComponent.inject(this)
    }

    private fun subscribeViewModel() {
        createTweetViewModel.loading.observe(viewLifecycleOwner, Observer {
            if(it) {
                progressBar.visibility = View.VISIBLE
                sendTweetButton.visibility = View.GONE
            } else {
                progressBar.visibility = View.GONE
                sendTweetButton.visibility = View.VISIBLE
            }
        })

        createTweetViewModel.goBackToTimeLine.observe(viewLifecycleOwner, Observer {
            if(it) {
                navigateToTimeline()
            }
        })

        sendTweetButton.setOnClickListener {
            val message = editTextMessage.text.toString()
            if (message.isNotEmpty()) {
                createTweetViewModel.createTweet(message)
            }
        }
    }

    private fun navigateToTimeline() {
        val action = R.id.action_createTweetFragment_to_homeTimelineFragment
        view?.findNavController()?.navigate(action)
    }
}