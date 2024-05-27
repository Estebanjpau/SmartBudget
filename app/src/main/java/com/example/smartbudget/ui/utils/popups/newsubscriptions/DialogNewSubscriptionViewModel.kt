package com.example.smartbudget.ui.utils.popups.newsubscriptions

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.example.smartbudget.di.FirebaseAuthUseCases
import com.example.smartbudget.di.FirebaseUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class DialogNewSubscriptionViewModel @Inject constructor(
    private val firebaseAuthUseCases: FirebaseAuthUseCases,
    private val firebaseUseCases: FirebaseUseCases
) : ViewModel() {

    val categories = arrayOf("Comida", "Educación", "Entretenimiento", "Hogar", "Medicina", "Mascota", "Ocio", "Otros", "Ropa", "Salud", "Transporte", "Viajes")
    suspend fun getSubscriptionslist(): MutableList<SubscriptionDataBase> {
        return firebaseUseCases.subscriptionUseCase.downloadSubcriptionDatabase()
    }

    fun loadInputData(amountEntered: Double, categoryEntered: String, titleEntered: String, dayBillingEntered: Int, backgroundEntered: String, colorText: String
    ) {
        firebaseUseCases.subscriptionUseCase.loadSubscription(
            amountEntered, categoryEntered, titleEntered, dayBillingEntered, backgroundEntered, colorText
        )
    }

    fun checkIfUserIsLogged(): Boolean {
        return firebaseAuthUseCases.validateSessionUseCase.checkUserSession()
    }
}