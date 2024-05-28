package com.example.smartbudget.ui.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.data.models.TransactionData

class DiffUtilsTransaction(
    private val newList: List<TransactionData>,
    private val oldList: List<TransactionData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].timestamp == newList[newItemPosition].timestamp
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}