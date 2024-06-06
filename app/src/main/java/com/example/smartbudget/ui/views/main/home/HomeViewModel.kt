package com.example.smartbudget.ui.views.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.di.FirebaseAuthUseCases
import com.example.smartbudget.di.FirebaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val firebaseUseCases: FirebaseUseCases,
    private val firebaseAuthUseCases: FirebaseAuthUseCases
) : ViewModel() {
    val subscriptionList: LiveData<MutableList<SubscriptionData>> get() = _subscriptionList
    private val _subscriptionList = MutableLiveData<MutableList<SubscriptionData>>()

    val transactionList: LiveData<MutableList<TransactionData>> get() = _transactionList
    private val _transactionList = MutableLiveData<MutableList<TransactionData>>()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun checkIfUserIsLogged(): Boolean {
        return firebaseAuthUseCases.validateSessionUseCase.checkUserSession()
    }

    fun subscriptionUserData() {
        coroutineScope.launch {
            _subscriptionList.postValue(
                firebaseUseCases.subscriptionUseCase.downloadSubscription()
                    .sortedByDescending { it.subscription }
                    .toMutableList()
            )
        }
    }
}