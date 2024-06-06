package com.example.smartbudget.ui.utils

import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject

object TimestampUtils {
    fun getCurrentTimestamp(): Int {
        val url = "https://worldtimeapi.org/api/timezone/America/Bogota"
        val client = OkHttpClient()
        val request = Request.Builder()
            .url(url)
            .build()
        val response = client.newCall(request).execute()
        val responseBody = response.body?.string()
        val jsonObject = JSONObject(responseBody ?: "")
        val timestamp = jsonObject.getLong("unixtime")

        return timestamp.toInt()
    }
}