package com.example.smartbudget.ui.views.main.home

import android.graphics.Color
import androidx.recyclerview.widget.RecyclerView
import com.example.smartbudget.data.models.SubscriptionData
import com.example.smartbudget.databinding.ListitemsubscriptionpreviewBinding
import com.example.smartbudget.ui.utils.resourceutils.IconUtils

class SubscriptionPreviewViewHolder(private val binding: ListitemsubscriptionpreviewBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(subscriptions: SubscriptionData){

        binding.ivSubscription
        binding.tvSubscriptionTitle.text = subscriptions.subscription
        binding.ivSubscription.setColorFilter(Color.parseColor("#" + (subscriptions.textColor ?: "333333")))

        binding.cvItem.setCardBackgroundColor(Color.parseColor("#" + (subscriptions.background ?: "FFFFFF")))

        val iconCategory = IconUtils.setIconCategory(subscriptions.category.toString())

        binding.ivSubscription.setImageResource(iconCategory)
    }

}