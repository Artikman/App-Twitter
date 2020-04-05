package com.example.twitter.ui.http

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.twitter.R
import kotlinx.android.synthetic.main.fragment_learn.*
import java.io.*
import java.net.URL
import java.net.URLConnection
import java.util.concurrent.Executors

class LearnHttpFragment :  Fragment(R.layout.fragment_learn) {

    private val executorService  = Executors.newFixedThreadPool(1)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btn_get.setOnClickListener {
            makeGetRequest()
        }
        btn_post.setOnClickListener {
            sendPostRequest()
        }
    }

    private fun makeGetRequest() {
        executorService.submit {
            val url = URL("https://httpbin.org/ip")
            val connection = url.openConnection()
            val reader = BufferedReader(InputStreamReader(connection.getInputStream()))
            val buffer = StringBuffer()
            var inputLine: String?
            while(reader.readLine().also { inputLine = it} != null) {
                buffer.append(inputLine)
            }

            textView_get.text = buffer.toString()

            reader.close()
        }
    }

    private fun sendPostRequest() {
        executorService.submit {
            val url = URL("http://httpbin.org/post")
            val urlParameters = "James Bond"
            val connection =
                url.openConnection() as URLConnection
            connection.doOutput = true
            val stream =
                OutputStreamWriter(connection.getOutputStream())
            stream.write(urlParameters)
            stream.close()
            val br =
                BufferedReader(InputStreamReader(connection.getInputStream()))
            val buffer = StringBuffer()
            var line: String?
            while (br.readLine().also { line = it } != null) {
                buffer.append(line)
            }

            textView_post.text = buffer.toString()

            br.close()
        }
    }
}