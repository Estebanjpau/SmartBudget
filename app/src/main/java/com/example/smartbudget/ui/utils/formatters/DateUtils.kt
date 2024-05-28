package com.example.smartbudget.ui.utils.formatters

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {

    fun convertTimestampToDateNumber(timestamp: Long): String {
        val date = Date(timestamp * 1000)
        val format = SimpleDateFormat("ddMMyyyyhhmmss", Locale.getDefault())
        return format.format(date)
    }

    fun convertDateNumbertoString(DateNumber: Long?): String {
        val dateString = DateNumber?.toString()?.substring(0, 8) ?: ""
        val dateFormat = SimpleDateFormat("ddMMyyyy", Locale.getDefault())
        val date = dateFormat.parse(dateString)

        val outputDateFormat = SimpleDateFormat("dd/MM/yy", Locale.getDefault())
        return outputDateFormat.format(date?:"")
    }
}