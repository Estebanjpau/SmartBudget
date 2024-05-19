package com.example.smartbudget.ui.utils

import android.view.Gravity
import android.view.View
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import com.example.smartbudget.R
import com.google.android.material.snackbar.Snackbar

object SnackbarUtils {
    fun showCustomSnackbar(view: View, message: String) {
        val snackbar = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
        val snackbarView = snackbar.view

        snackbarView.setBackgroundColor(ContextCompat.getColor(view.context, R.color.dark_grey))

        snackbar.setActionTextColor(ContextCompat.getColor(view.context, R.color.white))

        snackbar.duration = Snackbar.LENGTH_LONG

        val layoutParams = FrameLayout.LayoutParams(
            FrameLayout.LayoutParams.MATCH_PARENT,
            FrameLayout.LayoutParams.WRAP_CONTENT,
        )
        layoutParams.gravity = Gravity.BOTTOM
        layoutParams.bottomMargin = 80

        snackbar.view.layoutParams = layoutParams

        snackbar.show()
    }
}