package com.example.smartbudget.ui.utils.formatters

import java.text.NumberFormat

object CurrencyFormatter {
    fun String.toCurrencyString(): String {
        val cleanString = this.replace("[^\\d.]".toRegex(), "")

        val amount = cleanString.toDoubleOrNull() ?: 0.0

        val formattedAmount = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
        }.format(amount)

        return formattedAmount
    }
}