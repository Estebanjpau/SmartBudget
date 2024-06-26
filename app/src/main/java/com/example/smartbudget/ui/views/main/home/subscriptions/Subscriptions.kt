package com.example.smartbudget.ui.views.main.home.subscriptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentSubscriptionsBinding
import com.example.smartbudget.ui.utils.popup.subscriptions.DialogNewSubscription
import com.example.smartbudget.ui.views.main.home.HomeViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Subscriptions : Fragment() {

    @Inject
    lateinit var dialogHomeNewSubscription: DialogNewSubscription

    lateinit var binding: FragmentSubscriptionsBinding
    private lateinit var subscriptionAdapter: SubscriptionListAdapter

    private val viewModel: HomeViewModel by viewModels()

    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
            backToHome()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSubscriptionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()

        binding.button.setOnClickListener {
            dialogHomeNewSubscription.show(parentFragmentManager, "Movimiento")
        }

        binding.btnClose.setOnClickListener {
            backToHome()
        }

        viewModel.subscriptionList.observe(viewLifecycleOwner) { list ->
            list?.let {
                if (it.isNotEmpty()) {
                    subscriptionAdapter.updateList(it)
                }
            }
        }
    }

    private fun backToHome() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        val getCurrentSession = viewModel.checkIfUserIsLogged()
        if (getCurrentSession) {

            viewModel.subscriptionUserData()

            subscriptionAdapter = SubscriptionListAdapter(mutableListOf())

            binding.rvSubscriptions.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = subscriptionAdapter
            }
        }
    }
}