package com.example.smartbudget.ui.utils.formatters

import android.text.InputFilter
import android.text.Spanned
import com.example.smartbudget.ui.utils.formatters.TextUtils.ALLOWED_CHARACTERS_REGEX

class CapitalizeInputFilter : InputFilter {
    override fun filter(
        source: CharSequence?,
        start: Int,
        end: Int,
        dest: Spanned?,
        dstart: Int,
        dend: Int
    ): CharSequence? {
        if (source.isNullOrEmpty()) {
            return null
        }

        val input = source.subSequence(start, end).toString()
        val filteredInput = input.lowercase().replaceFirstChar { it.uppercase() }
        return if (filteredInput.matches(ALLOWED_CHARACTERS_REGEX)) filteredInput else ""
    }
}