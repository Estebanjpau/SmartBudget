package com.example.smartbudget.ui.views.fragments.history

import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ListitemtransactionBinding
import com.example.smartbudget.ui.utils.DateUtils
import com.example.smartbudget.ui.views.utils.CurrencyFormatter
import com.example.smartbudget.ui.views.utils.CurrencyFormatter.toCurrencyString

class HistoryTransactionListViewHolder(private val binding: ListitemtransactionBinding) : RecyclerView.ViewHolder(binding.root){

    fun bind(transaction : TransactionData) {
        val date = transaction.timestamp?.let { DateUtils.convertTimestampToDateNumber(it).toLong() }

        val amount = transaction.amount.toString()

        binding.tvItemAmount.text = amount.toCurrencyString()
        binding.tvItemDescription.text = transaction.description
        binding.tvItemDate.text = DateUtils.convertDateNumbertoString(date)
        binding.tvCategory.text = transaction.category
    }
}