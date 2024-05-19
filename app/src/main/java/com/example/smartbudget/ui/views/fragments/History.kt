package com.example.smartbudget.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.FragmentHistoryBinding
import com.example.smartbudget.domain.FirebaseDataRepository
import com.example.smartbudget.domain.FirebaseRepository
import com.example.smartbudget.ui.adapters.history.HistoryTransactionListAdapter
import com.example.smartbudget.ui.views.contracts.FragmentContract
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class History : Fragment(), FragmentContract {

    @Inject
    lateinit var firebaseDataRepository: FirebaseDataRepository

    @Inject
    lateinit var firebaseRepository: FirebaseRepository

    private var transactionList: MutableList<TransactionData> = mutableListOf()

    private lateinit var binding: FragmentHistoryBinding
    private lateinit var transactionAdapter: HistoryTransactionListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(Dispatchers.Main).launch {
            setupRecyclerView()

            if (transactionList.isEmpty()){
                binding.clNothingToShow.visibility = View.VISIBLE
                binding.clHistory.visibility = View.GONE
            } else {
                binding.clNothingToShow.visibility = View.GONE
                binding.clHistory.visibility = View.VISIBLE
            }
        }
    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private suspend fun setupRecyclerView(){
        val getCurrentSession = firebaseRepository.checkUserSession()
        if (getCurrentSession) {

            withContext(Dispatchers.IO) {
                transactionList =
                    firebaseDataRepository.downloadTransactionData().sortedByDescending { it.timestamp }
                        .toMutableList()
            }

            transactionAdapter = HistoryTransactionListAdapter(transactionList)

            withContext(Dispatchers.Main) {
                binding.rvTransactionHistory.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = transactionAdapter
                }
                transactionAdapter.notifyDataSetChanged()
            }
        }
    }
}