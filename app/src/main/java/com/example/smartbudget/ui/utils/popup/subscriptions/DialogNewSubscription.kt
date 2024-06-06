package com.example.smartbudget.ui.utils.popup.subscriptions

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.smartbudget.R
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.example.smartbudget.databinding.DialogNewsubscriptionBinding
import com.example.smartbudget.ui.utils.popup.SnackbarUtils
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AndroidEntryPoint
class DialogNewSubscription @Inject constructor() : DialogFragment() {

    private var subscriptionList = mutableListOf<SubscriptionDataBase>()
    private val viewModel: DialogNewSubscriptionViewModel by viewModels()

    private lateinit var binding: DialogNewsubscriptionBinding
    private lateinit var subscriptionAdapter: SubscriptionAutoCompleteAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        binding = DialogNewsubscriptionBinding.inflate(inflater)

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        dialog?.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog)
        dialog?.window?.setDimAmount(0.5f)

        var background = "white"
        var colorText = ""
        binding.cvBgcWhite.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_grey))

        val colorMap = mapOf(
            binding.cvBgcWhite to Pair("white", R.color.dark_grey),
            binding.cvBgBlue to Pair("blue", R.color.Blue),
            binding.cvBgcYellow to Pair("yellow", R.color.Yellow),
            binding.cvBgcPurple to Pair("purple", R.color.Purple),
            binding.cvBgcRed to Pair("red", R.color.Red),
            binding.cvBgGreen to Pair("green", R.color.Green)
        )

        fun resetColors() {
            colorMap.keys.forEach { view ->
                view.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            }
        }

        colorMap.forEach { (view, colorInfo) ->
            val (colorName, colorResId) = colorInfo
            view.setOnClickListener {
                if (background != colorName) {
                    resetColors()
                    view.setCardBackgroundColor(ContextCompat.getColor(requireContext(), colorResId))
                    background = colorName
                }
            }
        }

        binding.spinnerSubscriptionCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,viewModel.categories)

        binding.btnContinue.setOnClickListener {
            if (viewModel.checkIfUserIsLogged()){

                when (background){
                   "yellow" -> { background = "FEF1C1" ; colorText = "E0A73B"}
                   "blue" -> { background = "A8C9ED" ; colorText = "2778D3"}
                   "white" -> { background = "FFFFFF" ; colorText = "242424"}
                   "red" -> { background = "FEF1C1" ; colorText = "DD3B23"}
                   "green" -> { background = "80DFBC" ; colorText = "01C07A"}
                   "purple" -> { background = "A4A2EE" ; colorText = "4A44DD"}
                }

                val category = binding.spinnerSubscriptionCategory.selectedItem.toString()
                val amountInput = binding.etSubscriptionAmount.text.toString()
                var amount = 0.0
                if (amountInput.isNotEmpty()) amount = amountInput.toDouble()
                val title = binding.acSubscriptionTitle.text.toString()
                val dayBiling = binding.npSubscriptionInitDate.value

                if (title != "" && amount != 0.0){
                    viewModel.loadInputData(amount, category, title, dayBiling, background, colorText)
                    dismiss()
                } else {
                    if (amount == 0.0) {
                        binding.tvErrorAmount.visibility = View.VISIBLE
                    } else {
                        binding.tvErrorAmount.visibility = View.GONE
                    }

                    if (title == ""){
                        binding.tvErrorTitle.visibility = View.VISIBLE
                    } else {
                        binding.tvErrorTitle.visibility = View.GONE
                    }
                }

            } else {
                SnackbarUtils.showCustomSnackbar(binding.root, "Debes iniciar Sesión")
                dismiss()
            }
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        CoroutineScope(Dispatchers.IO).launch {
            subscriptionList = viewModel.getSubscriptionslist()

            val subscriptionTitlesList = subscriptionList.mapNotNull { it.title }

            withContext(Dispatchers.Main) {
                subscriptionAdapter =
                    SubscriptionAutoCompleteAdapter(requireContext(), subscriptionTitlesList)

                binding.acSubscriptionTitle.setAdapter(subscriptionAdapter)

                binding.acSubscriptionTitle.setOnItemClickListener { _, _, position, _ ->
                    val selectedTitle = subscriptionList[position].title
                    val selectedSubscription = subscriptionList.find { it.title == selectedTitle }

                    selectedSubscription?.amount?.toString().let { binding.etSubscriptionAmount.setText(it!!) }
                    binding.spinnerSubscriptionCategory.setSelection(viewModel.categories.indexOf(selectedSubscription?.category))
                    binding.acSubscriptionTitle.setText(selectedSubscription?.title.toString())
                }
            }
        }

        val dialog = builder.create()
        dialog.window?.setBackgroundDrawableResource(R.drawable.rounded_dialog)
        return dialog
    }
}