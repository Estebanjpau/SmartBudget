package com.example.smartbudget.ui.views.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionpreviewBinding
import com.example.smartbudget.ui.utils.diffutils.DiffUtilsSubscription

class SubscriptionPreviewAdapter(private var subscriptionList: List<SubscriptionData>) : RecyclerView.Adapter<SubscriptionPreviewViewHolder>(){

    fun updateList(newList: List<SubscriptionData>){
        val galleryDiff = DiffUtilsSubscription(subscriptionList, newList)
        val result = DiffUtil.calculateDiff(galleryDiff)
        subscriptionList = newList
        result.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubscriptionPreviewViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListitemsubscriptionpreviewBinding.inflate(inflater, parent, false)
        return SubscriptionPreviewViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscriptionList.size
    }

    override fun onBindViewHolder(holder: SubscriptionPreviewViewHolder, position: Int) {
        val transaction = subscriptionList[position]
        holder.bind(transaction)
    }
}