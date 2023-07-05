package com.example.smartbudget.ui.views.fragments

import com.example.smartbudget.R
import com.example.smartbudget.ui.views.contracts.FragmentContract

object FragmentFactory {
    fun createFragment(fragmentId: Int): FragmentContract {
        return when (fragmentId) {
            R.id.home -> Home()
            R.id.Budget -> Budget()
            R.id.History -> History()
            R.id.profile -> Profile()
            else -> throw IllegalArgumentException("Invalid fragment ID")
        }
    }
}