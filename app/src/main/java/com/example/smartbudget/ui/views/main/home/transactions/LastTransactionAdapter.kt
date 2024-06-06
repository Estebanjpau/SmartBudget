package com.example.smartbudget.ui.views.main.home.transactions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ItemLastTransactionBinding
import com.example.smartbudget.ui.utils.formatters.CurrencyFormatter.toCurrencyString
import com.example.smartbudget.ui.utils.formatters.DateUtils.convertDateNumbertoString
import com.example.smartbudget.ui.utils.formatters.DateUtils.convertTimestampToDateNumber

class LastTransactionAdapter(context: Context, transactions: List<TransactionData>) :
    ArrayAdapter<TransactionData>(context, 0, transactions) {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val binding: ItemLastTransactionBinding
        val view: View

        if (convertView == null) {
            binding = ItemLastTransactionBinding.inflate(inflater, parent, false)
            view = binding.root
            view.tag = binding
        } else {
            binding = convertView.tag as ItemLastTransactionBinding
            view = convertView
        }

        val transaction = getItem(position)

        transaction?.let {
            val amount = it.amount.toString()
            binding.tvTitle.text = amount.toCurrencyString()

            val date = it.timestamp?.let { time -> convertTimestampToDateNumber(time).toLong() }
            binding.tvDate.text = convertDateNumbertoString(date)
        }

        return view
    }
}