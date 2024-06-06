package com.example.smartbudget.ui.views.main

import com.example.smartbudget.R
import com.example.smartbudget.ui.views.main.budget.Budget
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.views.main.history.History
import com.example.smartbudget.ui.views.main.home.Home
import com.example.smartbudget.ui.views.main.profile.Profile

object FragmentFactory {

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