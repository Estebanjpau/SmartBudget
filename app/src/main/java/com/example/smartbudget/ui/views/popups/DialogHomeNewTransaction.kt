package com.example.smartbudget.ui.views.popups

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.fragment.app.DialogFragment
import com.example.smartbudget.domain.FirebaseRepository
import com.example.smartbudget.databinding.DialogHomenewtransactionBinding
import com.example.smartbudget.ui.utils.TextUtils
import com.example.smartbudget.ui.views.utils.SnackbarUtils
import javax.inject.Inject

class DialogHomeNewTransaction @Inject constructor(private val firebaseRepository: FirebaseRepository) : DialogFragment() {

    private lateinit var binding: DialogHomenewtransactionBinding
    private val categories = arrayOf("Comida", "Educación", "Entretenimiento", "Hogar", "Medicina", "Mascota", "Ocio", "Otros", "Ropa", "Salud", "Transporte", "Viajes")

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val inflater = requireActivity().layoutInflater
        binding = DialogHomenewtransactionBinding.inflate(inflater)

        binding.spinnerCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item, categories)

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        binding.btnContinue.setOnClickListener {
            val getCurrentSession = firebaseRepository.checkUserSession()

            if (getCurrentSession){

                val category = binding.spinnerCategory.selectedItem.toString()
                val amountInput = binding.etAmount.text.toString()
                var amount = 0.0
                if (amountInput.isNotEmpty()) amount = amountInput.toDouble()
                val description = binding.etDescription.text.toString()

                if (category != "" && amount != 0.0){
                    loadInputData(amount,category,description)
                    dismiss()
                } else {
                    if (amount == 0.0) {
                        binding.tvErrorAmount.visibility = View.VISIBLE
                    }
                }

            } else {
                SnackbarUtils.showCustomSnackbar(binding.root,"Debes iniciar Sesión")
                dismiss()
            }
        }

        binding.tvCancel.setOnClickListener {
            dismiss()
        }

        binding.etDescription.filters = TextUtils.getDescriptionInputFilters(20)

        return builder.create()
    }

    private fun loadInputData(amountEntered: Double, categoryEntered: String, descriptionEntered: String){
        firebaseRepository.loadTransactions(amountEntered, categoryEntered, descriptionEntered)
    }
}