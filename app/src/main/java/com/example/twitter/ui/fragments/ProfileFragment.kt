/*package com.example.twitter.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter.R
import com.example.twitter.TwitterApplication
import com.example.twitter.model.TweetItemPayLoad
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : Fragment(R.layout.fragment_profile) {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProvider.Factory

    private lateinit var profileViewModel: ProfileFragmentViewModel

    private var tweet: TweetItemPayLoad? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel = ViewModelProvider(this, viewModelProviderFactory).get(ProfileFragmentViewModel::class.java)
        super.onViewCreated(view, savedInstanceState)

        subscribeTimelineViewModel()

        viewpager!!.adapter = MyAdapter(fragmentManager, lifecycle)
        TabLayoutMediator(
            tabLayout!!,
            viewpager!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Твиты"
                }
            }).attach()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (context.applicationContext as TwitterApplication).appComponent.inject(this)
    }

    private inner class MyAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {

        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = HomeTimelineFragment()
            }
            return fragment!!
        }

        override fun getItemCount(): Int = 4
    }

    private fun subscribeTimelineViewModel() {
            textView_epam.text = tweet?.user?.name
    }
}*/