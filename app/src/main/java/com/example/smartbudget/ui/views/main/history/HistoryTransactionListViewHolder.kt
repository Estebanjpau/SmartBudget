package com.example.smartbudget.ui.views.main.history

import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ListitemtransactionBinding
import com.example.smartbudget.ui.utils.formatters.DateUtils
import com.example.smartbudget.ui.utils.formatters.CurrencyFormatter.toCurrencyString

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