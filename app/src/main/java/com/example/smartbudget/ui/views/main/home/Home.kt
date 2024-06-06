package com.example.smartbudget.ui.views.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentHomeBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.views.main.activity.MainActivity
import com.example.smartbudget.ui.views.main.history.History
import com.example.smartbudget.ui.views.main.history.HistoryViewModel
import com.example.smartbudget.ui.views.main.home.subscriptions.Subscriptions
import com.example.smartbudget.ui.views.main.home.transactions.DialogHomeNewTransaction
import com.example.smartbudget.ui.views.main.home.transactions.LastTransactionAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Home : Fragment(), FragmentContract {

    @Inject
    lateinit var dialogHomeNewTransaction: DialogHomeNewTransaction

    private val viewModel: HomeViewModel by viewModels()
    private val historyViewModel: HistoryViewModel by viewModels()

    private lateinit var binding: FragmentHomeBinding
    private lateinit var subcriptionPreviewAdapter: SubscriptionPreviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        historyViewModel.downloadTransactions()

        binding.btnNewMovement.setOnClickListener {
            showDialogNewTransaction()
        }
        
        binding.tvSubscriptions.setOnClickListener{
            showSubscriptionScreen()
        }
        binding.rvSubscriptionsPreview.setOnClickListener {
            showSubscriptionScreen()
        }

        binding.tvSeeMoreTransactions.setOnClickListener {
            val mainActivity = activity as MainActivity
            mainActivity.emulateHistoryClick()
        }

        viewModel.subscriptionList.observe(
            viewLifecycleOwner
        ) { list ->
            if (list.isNotEmpty()) subcriptionPreviewAdapter.updateList(list)
        }

        historyViewModel.transactionList.observe(
            viewLifecycleOwner
        ) { list ->
            if (list.isNotEmpty()) {
                val filteredList = list.sortedByDescending { it.timestamp }.take(5)
                val adapter = LastTransactionAdapter(requireContext(), filteredList)
                binding.lvLastTransactions.adapter = adapter
            }
        }
    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private fun showDialogNewTransaction() {
        dialogHomeNewTransaction.show(parentFragmentManager, "Movimiento")
    }
    
    private fun showSubscriptionScreen(){
        val subscriptionsFragment = Subscriptions()
        val transaction = parentFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
        transaction.replace(R.id.fl_optionsfragment, subscriptionsFragment)
        transaction.addToBackStack("home")
        transaction.commit()

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).visibility = View.GONE
    }

    private fun setupRecyclerView() {
        val getCurrentSession = viewModel.checkIfUserIsLogged()
        if (getCurrentSession) {

            viewModel.subscriptionUserData()

            subcriptionPreviewAdapter = SubscriptionPreviewAdapter(mutableListOf())

            binding.rvSubscriptionsPreview.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = subcriptionPreviewAdapter
            }
        }
    }
}