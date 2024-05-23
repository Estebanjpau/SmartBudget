package com.example.smartbudget.ui.views.utils

import java.text.NumberFormat

object CurrencyFormatter {
    fun String.toCurrencyString(): String {
        // Eliminar cualquier carácter no numérico
        val cleanString = this.replace("[^\\d]".toRegex(), "")

        // Convertir el string limpio a un número
        val amount = cleanString.toDoubleOrNull() ?: 0.0

        // Formatear el número como una cantidad de dinero
        val formattedAmount = NumberFormat.getCurrencyInstance().apply {
            maximumFractionDigits = 0
        }.format(amount)

        return formattedAmount
    }
}