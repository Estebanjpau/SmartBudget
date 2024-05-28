package com.example.smartbudget.ui.utils.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.smartbudget.data.models.SubscriptionData

class DiffUtilsSubscription(
    private val newList: List<SubscriptionData>,
    private val oldList: List<SubscriptionData>
): DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].subscription == newList[newItemPosition].subscription
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}