package com.example.smartbudget.ui.views.fragments.subscriptions

import androidx.lifecycle.ViewModel
import com.example.smartbudget.data.RepositoryFb
import com.example.smartbudget.data.models.SubscriptionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SubscriptionViewModel @Inject constructor(
    private val repositoryFb: RepositoryFb
) : ViewModel() {

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    lateinit var subscriptionList: MutableList<SubscriptionData>

    fun checkUserSession(): Boolean {
        return repositoryFb.checkSession()
    }

    fun downloadSubscriptionData() {
        coroutineScope.launch {
            val emptyList: MutableList<SubscriptionData> = mutableListOf()
            subscriptionList =
                repositoryFb.downloadSubscriptionData().sortedByDescending { it.subscription }
                    .toMutableList()
        }
    }
}