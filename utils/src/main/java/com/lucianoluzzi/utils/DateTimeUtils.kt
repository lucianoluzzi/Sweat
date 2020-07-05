package com.lucianoluzzi.utils

import java.text.SimpleDateFormat
import java.util.*

class DateTimeUtils {
    fun getDisplayableCurrentDate(): String {
        return SimpleDateFormat(DATE_DISPLAY_PATTERN, Locale.getDefault()).format(Date())
    }

    fun getDateTimeStamp(): String {
        val timeStamp = System.currentTimeMillis()
        return timeStamp.toString()
    }

    private companion object {
        const val DATE_DISPLAY_PATTERN = "dd/MM/yyyy"
    }
}