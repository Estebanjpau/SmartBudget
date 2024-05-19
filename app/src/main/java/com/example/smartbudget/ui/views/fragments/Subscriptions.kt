package com.example.smartbudget.ui.views.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.smartbudget.R
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.FragmentSubscriptionsBinding
import com.example.smartbudget.domain.FirebaseRepository
import com.example.smartbudget.domain.FirebaseSubscriptionDataRepo
import com.example.smartbudget.ui.adapters.subscription.SubscriptionListAdapter
import com.example.smartbudget.ui.utils.popups.DialogNewSubscription
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class Subscriptions : Fragment() {

    @Inject
    lateinit var dialogHomeNewSubscription: DialogNewSubscription

    lateinit var binding: FragmentSubscriptionsBinding
    private lateinit var subscriptionList: MutableList<SubscriptionData>
    private lateinit var subscriptionAdapter: SubscriptionListAdapter

    @Inject
    lateinit var firebaseRepository: FirebaseRepository

    @Inject
    lateinit var firebaseSubscriptionDataRepo: FirebaseSubscriptionDataRepo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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

        CoroutineScope(Dispatchers.IO).launch {
            setupRecyclerView()
        }
        binding.button.setOnClickListener {
            dialogHomeNewSubscription.show(requireFragmentManager(), "Movimiento")
        }

        binding.btnClose.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()

            requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).visibility = View.VISIBLE
        }
    }

    private suspend fun setupRecyclerView(){
        val getCurrentSession = firebaseRepository.checkUserSession()
        if (getCurrentSession) {
            withContext(Dispatchers.IO) {
                val emptyList: MutableList<SubscriptionData> = mutableListOf()
                subscriptionList =
                    firebaseSubscriptionDataRepo.downloadSubscriptionData().sortedByDescending { it.subscription }
                        .toMutableList()
                        ?: emptyList
            }

            subscriptionAdapter = SubscriptionListAdapter(subscriptionList)

            withContext(Dispatchers.Main) {
                binding.rvSubscriptions.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = subscriptionAdapter
                }
                subscriptionAdapter.notifyDataSetChanged()
            }
        }
    }
}