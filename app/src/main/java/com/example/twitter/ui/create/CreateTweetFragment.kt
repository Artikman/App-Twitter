package com.example.twitter.ui.create

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.twitter.R
import com.example.twitter.storage.TemporaryDataStorage
import kotlinx.android.synthetic.main.fragment_create.*

class CreateTweetFragment : Fragment() {

    private var v: View? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.create_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if(id == R.id.send_action) {
            savingDataToTemporaryStorage()
            val action =
                CreateTweetFragmentDirections.actionCreateTweetFragmentToAllTweetFragment()
            findNavController().navigate(action)
            return true
        }
        if(id == R.id.setting_action) {
            val action =
                CreateTweetFragmentDirections.actionCreateTweetFragmentToSettingFragment()
            findNavController().navigate(action)
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun savingDataToTemporaryStorage() {
        val name = editTextName.text.toString()
        val message = editTextMessage.text.toString()
        val action =
            CreateTweetFragmentDirections.actionCreateTweetFragmentToAllTweetFragment(
                name,
                message
            )
        v?.findNavController()?.navigate(action)
        val tweet = TemporaryDataStorage.newTweet(name, message)
        TemporaryDataStorage.create(tweet)
    }
}