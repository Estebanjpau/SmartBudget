package com.example.smartbudget.ui.adapters.history

import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ListitemtransactionBinding
import com.example.smartbudget.ui.utils.DateUtils

class HistoryTransactionListViewHolder(private val binding: ListitemtransactionBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(transaction : TransactionData) {
        val date = transaction.timestamp?.let { DateUtils.convertTimestampToDateNumber(it).toLong() }

        binding.tvItemAmount.text = transaction.amount.toString()
        binding.tvItemDescription.text = transaction.description
        binding.tvItemDate.text = DateUtils.convertDateNumbertoString(date)
        binding.tvCategory.text = transaction.category
    }
}