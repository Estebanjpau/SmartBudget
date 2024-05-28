package com.example.smartbudget.ui.views.main.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.smartbudget.R
import com.example.smartbudget.databinding.ActivityMainBinding
import com.example.smartbudget.di.SessionManager
import com.example.smartbudget.ui.views.main.contracts.FragmentContract
import com.example.smartbudget.ui.views.main.budget.Budget
import com.example.smartbudget.ui.views.main.FragmentFactory
import com.example.smartbudget.ui.views.main.history.History
import com.example.smartbudget.ui.views.main.home.Home
import com.example.smartbudget.ui.views.login.activity.LoginActivity
import com.example.smartbudget.ui.views.main.profile.Profile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding
    private var currentFragment = 0

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

         if (!viewModel.checkIfUserIsLogged()) {
             val intent = Intent(this, LoginActivity::class.java)
             this.finish()

             startActivity(intent)
        } else {
            binding.bottomNavigationBar.visibility = View.VISIBLE
            replaceFragment(Home())
            SessionManager.isLoggedIn = false
        }

        binding.bottomNavigationBar.setOnItemSelectedListener { menuItem ->
            val fragment = FragmentFactory.createFragment(menuItem.itemId)
            replaceFragment(fragment)
            true
        }
    }

    private fun replaceFragment(fragment: FragmentContract) {
        var fragmentValue: Int

        when (fragment) {
            is Home -> { fragmentValue = 1 }
            is Budget -> { fragmentValue = 2 }
            is History -> { fragmentValue = 3 }
            is Profile -> { fragmentValue = 4 }
            else -> { fragmentValue = 1 }
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        if (currentFragment == fragmentValue) {
            return
        } else if (currentFragment > fragmentValue){
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            fragmentTransaction.replace(R.id.fl_navigationfragment, fragment as Fragment)
            fragmentTransaction.commitNow()
        } else {
            fragmentTransaction.setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            fragmentTransaction.replace(R.id.fl_navigationfragment, fragment as Fragment)
            fragmentTransaction.commitNow()
        }

        currentFragment = fragmentValue
    }
}