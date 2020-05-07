package com.example.twitter.ui.timeline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.twitter.R
import com.example.twitter.databinding.ItemTweetBinding
import com.example.twitter.model.TweetItemPayLoad
import com.example.twitter.util.DateParsing
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tweet.*

class AllTweetAdapter(private var tweetList: List<TweetItemPayLoad>) :
        RecyclerView.Adapter<AllTweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false))

    override fun getItemCount() = tweetList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweetList[position]
        holder.bind(tweet)
    }

    class ViewHolder(override val containerView: View) :
           RecyclerView.ViewHolder(containerView), LayoutContainer {

       private val requestManager = Glide.with(containerView.context)

       fun bind(tweet: TweetItemPayLoad) {
           requestManager.clear(person)

           val tweetBinding: ItemTweetBinding? = null

           tweetBinding?.viewmodel = tweet
           tweetBinding?.executePendingBindings()

           requestManager.load(tweet.user.profileImageUrlHttps)
               .circleCrop()
               .into(person)

           person.setOnClickListener {
               it.findNavController().navigate(R.id.action_homeTimelineFragment_to_profileFragment)
           }

           name.text = tweet.user.name
           time.text = DateParsing.gettingDate(tweet.createdAt)
           idName.text = tweet.user.screenName
           status.text = tweet.text
           comments.text = tweet.retweetCount.toString()
           lock.text = tweet.retweetCount.toString()
           likes.text = tweet.favoriteCount.toString()
       }
   }
}