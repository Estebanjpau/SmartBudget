package com.example.smartbudget.ui.utils.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun convertTimestampToDateNumber(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val format = SimpleDateFormat("yyyyMMddhhmmss", Locale.getDefault())
        return format.format(date)
    }

    fun convertDateNumbertoString(dateNumber: Long?): String {
        val dateString = dateNumber?.toString()?.substring(0, 8) ?: ""
        val dateFormat = SimpleDateFormat("yyyyMMdd", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        val outputDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return outputDateFormat.format(date?:"")
    }
}