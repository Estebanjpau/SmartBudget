package com.example.smartbudget.ui.views.main.budget

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentProfileBinding
import com.example.smartbudget.ui.views.main.contracts.FragmentContract

class Budget : Fragment(), FragmentContract {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_budget, container, false)

    }

    override fun display() {
        TODO("Not yet implemented")
    }
}