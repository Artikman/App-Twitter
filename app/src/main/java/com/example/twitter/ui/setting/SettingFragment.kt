package com.example.twitter.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.twitter.R
import com.example.twitter.ui.dm.DirectMessageFragment
import com.example.twitter.ui.home.HomeTimelineFragment
import com.example.twitter.ui.http.LearnHttpFragment
import com.example.twitter.ui.search.SearchFragment
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_setting.*

@Suppress("DEPRECATION")
class SettingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_setting, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewpager!!.adapter = MyAdapter(fragmentManager, lifecycle)
        TabLayoutMediator(
            tabLayout!!,
            viewpager!!,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> tab.text = "Home"
                    1 -> tab.text = "Search"
                    2 -> tab.text = "Direct Message"
                    3 -> tab.text = "HTTP"
                }
            }).attach()
    }

    private inner class MyAdapter(fm: FragmentManager?, lifecycle: Lifecycle) : FragmentStateAdapter(fm!!, lifecycle) {

        override fun createFragment(position: Int): Fragment {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment =
                    HomeTimelineFragment()
                1 -> fragment = SearchFragment()
                2 -> fragment = DirectMessageFragment()
                3 -> fragment = LearnHttpFragment()
            }
            return fragment!!
        }

        override fun getItemCount(): Int = 4

    }
}