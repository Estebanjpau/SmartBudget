package com.example.smartbudget.ui.views.main.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbudget.databinding.FragmentHistoryBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class History : Fragment(), FragmentContract {

    private val viewModel: HistoryViewModel by viewModels()

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

        setupRecyclerView()

        viewModel.transactionList.observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                if (it.isNotEmpty()) {
                    transactionAdapter.updateList(it)
                    binding.clNothingToShow.visibility = View.GONE
                    binding.clHistory.visibility = View.VISIBLE
                } else {
                    binding.clNothingToShow.visibility = View.VISIBLE
                    binding.clHistory.visibility = View.GONE
                }
            }
        })

    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private fun setupRecyclerView() {
        val getCurrentSession = viewModel.checkIfUserIsLogged()
        if (getCurrentSession) {

            viewModel.downloadTransactions()

            transactionAdapter = HistoryTransactionListAdapter(mutableListOf())

            binding.rvTransactionHistory.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = transactionAdapter
            }
        }
    }
}