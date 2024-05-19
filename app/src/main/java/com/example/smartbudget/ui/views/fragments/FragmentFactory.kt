package com.example.smartbudget.ui.views.fragments

import com.example.smartbudget.R
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.views.fragments.profile.Profile

object FragmentFactory {

    private var lastActionId: Int? = null

    fun createFragment(fragmentId: Int): FragmentContract {

        return when (fragmentId) {
            R.id.home -> Home()
            R.id.budget -> Budget()
            R.id.history -> History()
            R.id.profile -> Profile()
            else -> throw IllegalArgumentException("Invalid fragment ID")
        }
    }
}