package com.example.twitter.ui.dm

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.twitter.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.alert_dialog_direct_message.view.*
import kotlinx.android.synthetic.main.fragment_direct_message.*

class DirectMessageFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_direct_message, container, false)
    }

    @SuppressLint("InflateParams")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showDM.setOnClickListener {
            val dialogView = layoutInflater.inflate(R.layout.alert_dialog_direct_message, null)
            val dialog = MaterialAlertDialogBuilder(context)
            dialog.setView(dialogView)
            dialog.setCancelable(false)

            val alertDialog = dialog.show()

            dialogView.btnAccept.setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }
}