package com.example.smartbudget.ui.views.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentHomeBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.utils.popups.DialogHomeNewTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Home : Fragment(), FragmentContract {

    @Inject
    lateinit var dialogHomeNewTransaction: DialogHomeNewTransaction

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNewMovement.setOnClickListener {
            showDialogNewTransaction()
        }
        
        binding.tvSubscriptions.setOnClickListener{
            showSubscriptionScreen()
        }
        binding.svOptions.setOnClickListener{
            showSubscriptionScreen()
        }
    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private fun showDialogNewTransaction() {
        dialogHomeNewTransaction.show(requireFragmentManager(), "Movimiento")
    }
    
    private fun showSubscriptionScreen(){
        val subscriptionsFragment = Subscriptions()
        val transaction = requireFragmentManager().beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_up, R.anim.slide_down)
        transaction.replace(R.id.fl_optionsfragment, subscriptionsFragment)
        transaction.addToBackStack("home")
        transaction.commit()

        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationBar).visibility = View.GONE
    }
}