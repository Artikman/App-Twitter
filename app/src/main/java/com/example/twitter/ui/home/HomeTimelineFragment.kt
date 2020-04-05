package com.example.twitter.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.twitter.R
import com.example.twitter.storage.TemporaryDataStorage
import com.example.twitter.ui.timeline.adapter.AllTweetAdapter
import kotlinx.android.synthetic.main.fragment_all_tweet.*
import kotlinx.android.synthetic.main.item_tweet.*

class HomeTimelineFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_all_tweet, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.timeline_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.create_action) {
            val action =
                HomeTimelineFragmentDirections.actionAllTweetFragmentToCreateTweetFragment()
            findNavController().navigate(action)
            return true
        }
        if(id == R.id.setting_action) {
            val action =
                HomeTimelineFragmentDirections.actionAllTweetFragmentToSettingFragment()
            findNavController().navigate(action)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val args =
                HomeTimelineFragmentArgs.fromBundle(
                    it
                )
            name?.text = args.name
            status?.text = args.message
        }
        val tweetsList = TemporaryDataStorage.read()
        tweetRecyclerView.adapter = AllTweetAdapter(tweetsList)
        tweetRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }
}