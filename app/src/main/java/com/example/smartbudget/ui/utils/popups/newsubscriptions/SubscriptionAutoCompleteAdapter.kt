package com.example.smartbudget.ui.utils.popups.newsubscriptions

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.smartbudget.data.models.SubscriptionDataBase

class SubscriptionAutoCompleteAdapter(
    context: Context,
    private val subscriptionTitlesList: List<String>
) : ArrayAdapter<String>(context, android.R.layout.simple_dropdown_item_1line), Filterable {

    private var filteredList: List<String> = subscriptionTitlesList

    override fun getCount(): Int {
        return filteredList.size
    }

    override fun getItem(position: Int): String? {
        return filteredList[position]
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val results = FilterResults()

                constraint?.let { query ->
                    if (query.isEmpty()) {
                        filteredList = subscriptionTitlesList
                    } else {
                        filteredList = subscriptionTitlesList.filter { title ->
                            title.contains(query, ignoreCase = true)
                        }
                    }

                    results.values = filteredList
                    results.count = filteredList.size
                }

                return results
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults) {
                filteredList = results.values as? List<String> ?: emptyList()
                notifyDataSetChanged()
            }
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(
            android.R.layout.simple_dropdown_item_1line,
            parent,
            false
        )

        val textView = view.findViewById<TextView>(android.R.id.text1)
        textView.text = getItem(position)

        return view
    }
}