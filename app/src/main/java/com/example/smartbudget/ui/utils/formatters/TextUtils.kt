package com.example.smartbudget.ui.utils.formatters

import android.text.InputFilter
import android.text.InputFilter.AllCaps

object TextUtils {
    val ALLOWED_CHARACTERS_REGEX = "[a-zA-Z0-9 ]*".toRegex()

    fun getDescriptionInputFilters(maxCharacters: Int): Array<InputFilter> {
        return arrayOf(
            InputFilter.LengthFilter(maxCharacters),
            CapitalizeInputFilter()
        )
    }
}