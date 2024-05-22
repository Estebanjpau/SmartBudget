package com.example.smartbudget.ui.views.fragments.login

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.R
import com.example.smartbudget.di.FirebaseAuthUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseAuthUseCases
) : ViewModel() {

    private val _loadingState = MutableLiveData<Boolean>()
    val loadingState: LiveData<Boolean> = _loadingState

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> = _errorMessage

    private val _navigateToHomeScreen = MutableLiveData<Unit>()
    val navigateToHomeScreen: LiveData<Unit> = _navigateToHomeScreen

    fun navigateToSignUpScreen(requireActivity: FragmentActivity) {
        requireActivity.supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            .replace(R.id.fl_loginFragment, SignUp())
            .addToBackStack("SignUp")
            .commit()
    }
}
