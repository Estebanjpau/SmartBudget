package com.example.smartbudget.ui.views.models

import android.graphics.Color
import androidx.core.content.ContextCompat
import androidx.core.graphics.toColorInt
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionBinding

class SubscriptionViewHolder(private val binding: ListitemsubscriptionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriptions: SubscriptionData){
        binding.ivSubscription
        binding.tvSubscriptionAmount.text = subscriptions.amount.toString()
        binding.tvSubscriptionCategory.text = subscriptions.category
        binding.tvSubscriptionTitle.text = subscriptions.subscription
        binding.ivSubscription.setColorFilter(Color.parseColor("#" + (subscriptions.textColor ?: "333333")))
        binding.cvSubscription.setCardBackgroundColor(Color.parseColor("#" + (subscriptions.background ?: "FFFFFF")))
    }

}