package com.example.smartbudget.ui.views.activities

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.smartbudget.R
import com.example.smartbudget.databinding.ActivityMainBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.views.fragments.Budget
import com.example.smartbudget.ui.views.fragments.FragmentFactory
import com.example.smartbudget.ui.views.fragments.History
import com.example.smartbudget.ui.views.fragments.Home
import com.example.smartbudget.ui.views.fragments.LoginFragment
import com.example.smartbudget.ui.views.fragments.Profile
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val isLoggedIn  = sharedPreferences.getBoolean("isLoggedIn", true)
        if (!isLoggedIn ) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fl_loginFragment, LoginFragment())
                .commit()
            sharedPreferences.edit().putBoolean("isLoggedIn", false).apply()
        } else {
            supportFragmentManager.beginTransaction()
                .remove(LoginFragment())
                .commit()
            replaceFragment(Home())
        }

        binding.bottomNavigationBar.setOnItemSelectedListener { menuItem ->
            val fragment = FragmentFactory.createFragment(menuItem.itemId)
            replaceFragment(fragment)
            true
        }
    }

    private fun replaceFragment(fragment: FragmentContract) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(
            android.R.anim.slide_in_left,
            android.R.anim.slide_out_right
        )
        fragmentTransaction.replace(R.id.fl_navigationfragment, fragment as Fragment)
        fragmentTransaction.commit()
    }
}