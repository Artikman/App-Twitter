package com.example.twitter.ui.search

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twitter.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.alert_dialog_search.view.*
import kotlinx.android.synthetic.main.fragment_search.*

class SearchFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showSearch.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_search, null)
            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setView(dialogView)
            dialog.setCancelable(false)

            val alertDialog = dialog.show()

            dialogView.img_button.setOnClickListener {
                alertDialog.dismiss()
            }

            dialogView.cross.setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }
}