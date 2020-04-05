package com.example.twitter.storage

import java.text.SimpleDateFormat
import java.util.*

object DateParsing {
    fun gettingDate(date: Date): String {
        return SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(date)
    }
}