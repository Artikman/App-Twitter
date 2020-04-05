package com.example.twitter.ui.timeline.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.twitter.R
import com.example.twitter.model.TweetItem
import com.example.twitter.storage.DateParsing
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_tweet.*

class AllTweetAdapter(private val tweetList : List<TweetItem>) :
        RecyclerView.Adapter<AllTweetAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = when(viewType) {
            TWEET ->
                inflater.inflate(R.layout.item_tweet, parent, false)
            PROMOTION ->
                inflater.inflate(R.layout.item_tweet_promotion, parent, false)
            else -> (throw IllegalArgumentException("Error"))
        }
        return ViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int {
        return tweetList[position].type
    }

    override fun getItemCount(): Int {
       return tweetList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val tweet = tweetList[position]
        holder.bind(tweet)
    }

    class ViewHolder(override val containerView: View) :
            RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bind(tweet: TweetItem) {
            when(tweet.type) {
                TWEET -> {
                    name.text = tweet.name
                    time.text = DateParsing.gettingDate(tweet.date)
                    idName.text = tweet.idName
                    status.text = tweet.message
                    comments.text = tweet.comments.toString()
                    lock.text = tweet.lock.toString()
                    likes.text = tweet.likes.toString()
                }
                PROMOTION -> {
                }
            }
        }
    }

    companion object {
        const val TWEET = 0
        const val PROMOTION = 1
    }
}