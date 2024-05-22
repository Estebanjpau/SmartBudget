package com.example.smartbudget.ui.views.fragments.login

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.smartbudget.R
import com.example.smartbudget.di.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

    fun navigateToSignInScreen(requireActivity: FragmentActivity) {
        requireActivity.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
            )
            .replace(R.id.fl_loginFragment, SignIn())
            .commit()
    }

}