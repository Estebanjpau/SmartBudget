package com.example.smartbudget.ui.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.smartbudget.data.models.SubscriptionDataBase

class SubscriptionAutoCompleteAdapter(
    context: Context,
    private val subscriptions: List<SubscriptionDataBase>,
    private val subscriptionTitlesList: List<String> // Nueva lista de títulos para filtrar
) : ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line) {

    private val filter = SubscriptionFilter()

    override fun getCount(): Int {
        return subscriptions.size
    }

    override fun getItem(position: Int): String? {
        return subscriptions[position].title
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_dropdown_item_1line,
            parent,
            false
        )

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)

        textView.setTextColor(ContextCompat.getColor(context, android.R.color.black))

        return view
    }

    override fun getFilter(): Filter {
        return filter
    }

    inner class SubscriptionFilter : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val results = FilterResults()

            constraint?.let {
                val filteredList = if (it.isEmpty()) {
                    subscriptionTitlesList // Si el texto de filtro está vacío, muestra todos los títulos
                } else {
                    subscriptionTitlesList.filter { title ->
                        title.contains(it.toString(), ignoreCase = true) // Filtrar por título que contiene el texto de filtro
                    }
                }

                results.values = filteredList
                results.count = filteredList.size
            }

            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults) {
            clear()
            if (results.count > 0) {
                addAll(results.values as List<String>)
            }
            notifyDataSetChanged()
        }
    }
}