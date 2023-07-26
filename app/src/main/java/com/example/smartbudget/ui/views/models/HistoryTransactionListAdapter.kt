package com.example.smartbudget.ui.views.models

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ListitemtransactionBinding

class HistoryTransactionListAdapter(private val transactionList: List<TransactionData>) :
    RecyclerView.Adapter<HistoryTransactionListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryTransactionListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemtransactionBinding.inflate(inflater, parent, false)
        return HistoryTransactionListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: HistoryTransactionListViewHolder, position: Int) {
        val transaction = transactionList[position]
        holder.bind(transaction)
    }
}