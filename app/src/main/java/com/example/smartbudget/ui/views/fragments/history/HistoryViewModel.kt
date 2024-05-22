package com.example.smartbudget.ui.views.fragments.history

import androidx.lifecycle.ViewModel
import com.example.smartbudget.data.RepositoryFb
import com.example.smartbudget.data.models.TransactionData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(
    private val repositoryFb: RepositoryFb
) : ViewModel() {

    var transactionList: MutableList<TransactionData> = mutableListOf()

    private val coroutineScope = CoroutineScope(Dispatchers.IO)

    fun checkUserSession(): Boolean {
        return repositoryFb.checkSession()
    }

    fun downloadTransactions() {
        coroutineScope.launch {
            transactionList = repositoryFb.downloadTransactions().sortedByDescending { it.timestamp }
                    .toMutableList()
        }
    }
}