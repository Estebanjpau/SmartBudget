package com.example.smartbudget.ui.views.fragments.home.subscriptions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionBinding
import com.example.smartbudget.ui.views.utils.DiffUtilsSubscription

class SubscriptionListAdapter(private var subscriptionList: List<SubscriptionData>) : RecyclerView.Adapter<SubscriptionViewHolder>(){

    fun updateList(newList: List<SubscriptionData>){
        val galleryDiff = DiffUtilsSubscription(subscriptionList, newList)
        val result = DiffUtil.calculateDiff(galleryDiff)
        subscriptionList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemsubscriptionBinding.inflate(inflater, parent, false)
        return SubscriptionViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscriptionList.size
    }

    override fun onBindViewHolder(holder: SubscriptionViewHolder, position: Int) {
        val transaction = subscriptionList[position]
        holder.bind(transaction)
    }
}