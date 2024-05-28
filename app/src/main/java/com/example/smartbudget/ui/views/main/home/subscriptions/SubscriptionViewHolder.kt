package com.example.smartbudget.ui.views.main.home.subscriptions

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionBinding
import com.example.smartbudget.ui.utils.formatters.CurrencyFormatter.toCurrencyString

class SubscriptionViewHolder(private val binding: ListitemsubscriptionBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriptions: SubscriptionData){
        val amount = subscriptions.amount.toString()

        binding.ivSubscription
        binding.tvSubscriptionAmount.text = amount.toCurrencyString()
        binding.tvSubscriptionCategory.text = subscriptions.category
        binding.tvSubscriptionTitle.text = subscriptions.subscription
        binding.ivSubscription.setColorFilter(Color.parseColor("#" + (subscriptions.textColor ?: "333333")))
        binding.cvSubscription.setCardBackgroundColor(Color.parseColor("#" + (subscriptions.background ?: "FFFFFF")))
    }

}