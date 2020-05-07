package com.example.twitter.util

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateParsing {
    fun gettingDate(date: String): String {
        val outputFormat: DateFormat = SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.US)
        val inputFormat: DateFormat = SimpleDateFormat("MMM-dd", Locale.US)
        val date1: Date? = outputFormat.parse(date)
        val calendar: Calendar = Calendar.getInstance()
        val now: Date = calendar.time
        calendar.add(Calendar.DAY_OF_YEAR, -1)
        val yesterday: Date = calendar.time
        val diff: Long = now.time - date1!!.time
        val minute: Long = TimeUnit.MINUTES.convert(diff, TimeUnit.MILLISECONDS)
        val hour: Long = TimeUnit.HOURS.convert(diff, TimeUnit.MILLISECONDS)
        val day: Long = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)
        if(hour == 0.toLong()) {
            return "$minute mins"
        }
        if(hour < 24 && date1.day == now.day) {
            return "$hour h"
        }
        if(date1.year == yesterday.year && date1.month == yesterday.month && date1.day == yesterday.day) {
            return "yesterday"
        }
        if(day < 31 && date1.month == now.month) {
            return "$day d"
        }
        return inputFormat.format(date1)
    }
}