package com.example.smartbudget.ui.views.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionBinding

class SubscriptionListAdapter(private val transactionList: List<SubscriptionData>) : RecyclerView.Adapter<SubscriptionViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemsubscriptionBinding.inflate(inflater, parent, false)
        return SubscriptionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }
}