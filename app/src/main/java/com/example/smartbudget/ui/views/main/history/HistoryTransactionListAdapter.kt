package com.example.smartbudget.ui.views.main.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.TransactionData
import com.example.smartbudget.databinding.ListitemtransactionBinding
import com.example.smartbudget.ui.utils.diffutils.DiffUtilsTransaction

class HistoryTransactionListAdapter(private var transactionList: List<TransactionData>) :
    RecyclerView.Adapter<HistoryTransactionListViewHolder>(){

    fun updateList(newList: List<TransactionData>){
        val galleryDiff = DiffUtilsTransaction(transactionList, newList)
        val result = DiffUtil.calculateDiff(galleryDiff)
        transactionList = newList
        result.dispatchUpdatesTo(this)
    }

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