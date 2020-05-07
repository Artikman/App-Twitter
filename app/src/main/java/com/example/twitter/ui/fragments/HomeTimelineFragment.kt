package com.example.twitter.ui.fragments

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitter.R
import com.example.twitter.TwitterApplication
import com.example.twitter.databinding.ItemTweetBinding
import com.example.twitter.model.TweetItemPayLoad
import com.example.twitter.ui.timeline.adapter.AllTweetAdapter
import com.example.twitter.ui.viewModel.HomeTweetViewModel
import com.luseen.spacenavigation.SpaceItem
import com.luseen.spacenavigation.SpaceOnClickListener
import kotlinx.android.synthetic.main.fragment_all_tweet.*
import kotlinx.android.synthetic.main.item_tweet.*
import javax.inject.Inject

class HomeTimelineFragment: Fragment(R.layout.fragment_all_tweet) {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory
    private lateinit var timelineViewModel: HomeTweetViewModel

    lateinit var notificationManager: NotificationManager
    lateinit var notificationChannel: NotificationChannel
    lateinit var builder: Notification.Builder

    private val channelId = "com.example.twitter.ui.main"
    private val description =  "My Notification"

    private lateinit var viewModel: TweetItemPayLoad
    private var dataBinding: ItemTweetBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding?.viewmodel = viewModel
        dataBinding?.lifecycleOwner = this
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        timelineViewModel = ViewModelProvider(this, viewModelProviderFactory).get(HomeTweetViewModel::class.java)

        super.onViewCreated(view, savedInstanceState)

        subscribeTimelineViewModel()

        notificationsMessage()

        space.initWithSaveInstanceState(savedInstanceState)
        space.addSpaceItem(SpaceItem("", R.drawable.ic_home_black_24dp))
        space.addSpaceItem(SpaceItem("", R.drawable.ic_search_black_24dp))
        space.addSpaceItem(SpaceItem("", R.drawable.ic_notifications_none_black_24dp))
        space.addSpaceItem(SpaceItem("", R.drawable.ic_mail_outline_black_24dp))

        space.setSpaceOnClickListener(object : SpaceOnClickListener {
            override fun onCentreButtonClick() {
                val action = R.id.action_homeTimelineFragment_to_createTweetFragment
                view.findNavController().navigate(action)
            }

            override fun onItemReselected(itemIndex: Int, itemName: String?) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onItemClick(itemIndex: Int, itemName: String?) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as TwitterApplication).appComponent.inject(this)
    }

    private fun subscribeTimelineViewModel() {
        tweetRecyclerView.adapter = null
        timelineViewModel.getTweets().observe(viewLifecycleOwner, Observer {
            tweetRecyclerView.adapter = AllTweetAdapter(it)
            tweetRecyclerView.layoutManager = LinearLayoutManager(context)
        })
    }

    private fun notificationsMessage() {
        notificationManager = requireActivity().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        action_bar_title.setOnClickListener {

            val intent = Intent(context, HomeTimelineFragment::class.java)
            val pendingIntent = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                notificationChannel =
                    NotificationChannel(channelId, description, NotificationManager.IMPORTANCE_HIGH)
                notificationChannel.enableLights(true)
                notificationChannel.lightColor = Color.RED
                notificationChannel.enableVibration(true)
                notificationManager.createNotificationChannel(notificationChannel)

                builder = Notification.Builder(context, channelId)
                    .setContentTitle("New notifications")
                    .setContentText("New message")
                    .setSmallIcon(R.drawable.ic_message_black_24dp)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
            }
            else {
                builder = Notification.Builder(context)
                    .setContentTitle("New notifications")
                    .setContentText("New message")
                    .setSmallIcon(R.drawable.ic_message_black_24dp)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)
            }
            notificationManager.notify(1, builder.build())
        }
    }
}