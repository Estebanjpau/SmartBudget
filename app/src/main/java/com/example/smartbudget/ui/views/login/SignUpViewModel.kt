package com.example.smartbudget.ui.views.login

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.R
import com.example.smartbudget.di.FirebaseAuthUseCases
import com.example.smartbudget.ui.utils.formatters.RegexUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {

    private val _nickname = MutableLiveData<String>()
    val nickname: LiveData<String> get() = _nickname

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> get() = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> get() = _password

    private val _confirmPassword = MutableLiveData<String>()
    val confirmPassword: LiveData<String> get() = _confirmPassword

    private val _isFormValid = MediatorLiveData<Boolean>().apply {
        addSource(_nickname) { validateForm() }
        addSource(_email) { validateForm() }
        addSource(_password) { validateForm() }
        addSource(_confirmPassword) { validateForm() }
    }

    fun setNickname(nickname: String) { _nickname.value = nickname }

    fun setEmail(email: String) { _email.value = email }

    fun setPassword(password: String) { _password.value = password }

    fun setConfirmPassword(confirmPasswordpassword: String) { _confirmPassword.value = confirmPasswordpassword }

    private fun validateForm() {
        _isFormValid.value = RegexUtils.isValidEmail(_email.value ?: "") &&
                RegexUtils.isValidNickname(_nickname.value ?: "") &&
                RegexUtils.isValidPassword(_password.value ?: "") &&
                RegexUtils.isValidPassword(_confirmPassword.value ?: "") &&
                _confirmPassword.value == _password.value
    }
    val isFormValid: LiveData<Boolean> get() = _isFormValid
}