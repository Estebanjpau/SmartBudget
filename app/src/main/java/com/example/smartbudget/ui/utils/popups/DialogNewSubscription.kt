package com.example.smartbudget.ui.utils.popups

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.example.smartbudget.R
import com.example.smartbudget.data.models.SubscriptionDataBase
import com.example.smartbudget.databinding.DialogNewsubscriptionBinding
import com.example.smartbudget.domain.FirebaseRepository
import com.example.smartbudget.domain.FirebaseSubscriptionDataRepo
import com.example.smartbudget.domain.FirebaseSubscriptionRepo
import com.example.smartbudget.ui.adapters.SubscriptionAutoCompleteAdapter
import com.example.smartbudget.ui.utils.SnackbarUtils
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DialogNewSubscription @Inject constructor(private val firebaseSubscriptionRepo: FirebaseSubscriptionRepo, private val firebaseRepository: FirebaseRepository, private val firebaseSubscriptionDataRepo: FirebaseSubscriptionDataRepo) : DialogFragment() {

    private lateinit var binding: DialogNewsubscriptionBinding
    private val categories = arrayOf("Comida", "Educación", "Entretenimiento", "Hogar", "Medicina", "Mascota", "Ocio", "Otros", "Ropa", "Salud", "Transporte", "Viajes")
    private var subscriptionList = mutableListOf<SubscriptionDataBase>()
    private lateinit var subscriptionAdapter: SubscriptionAutoCompleteAdapter

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val inflater = requireActivity().layoutInflater
        binding = DialogNewsubscriptionBinding.inflate(inflater)

        val builder = AlertDialog.Builder(requireContext())
            .setView(binding.root)

        CoroutineScope(Dispatchers.IO).launch{
            subscriptionList = firebaseSubscriptionDataRepo.downloadSubscriptionDataBase()


            CoroutineScope(Dispatchers.Main).launch {
                val subscriptionTitlesList = subscriptionList.map { it.title ?: "" }
                subscriptionAdapter = SubscriptionAutoCompleteAdapter(requireContext(), subscriptionList, subscriptionTitlesList)

                binding.acSubscriptionTitle.setAdapter(subscriptionAdapter)

                binding.acSubscriptionTitle.setOnItemClickListener { _, _, position, _ ->
                    val selectedTitle = subscriptionList[position].title
                    val selectedSubscription = subscriptionList.find { it.title == selectedTitle }

                    selectedSubscription?.amount?.toInt().let { binding.etSubscriptionAmount.setText(it!!) }
                    binding.spinnerSubscriptionCategory.setSelection(categories.indexOf(selectedSubscription?.category.toString()))
                    binding.acSubscriptionTitle.setText(selectedSubscription?.title.toString())
                }
            }
        }

        var background = "white"
        var colorText = ""
        binding.cvBgcWhite.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_grey))

        fun resetColors(){
            binding.cvBgcWhite.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            binding.cvBgcYellow.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            binding.cvBgcRed.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            binding.cvBgBlue.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            binding.cvBgcPurple.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
            binding.cvBgGreen.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.LightBackground))
        }

        binding.cvBgcWhite.setOnClickListener {
            if (background != "white"){
                resetColors()
                binding.cvBgcWhite.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.dark_grey))
                background = "white"
            } else { resetColors() }
        }

        binding.cvBgBlue.setOnClickListener {
            if (background != "blue"){
                resetColors()
                binding.cvBgBlue.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.Blue))
                background = "blue" } else { resetColors() }
        }

        binding.cvBgcYellow.setOnClickListener {
            if (background != "yellow"){
                resetColors()
                binding.cvBgcYellow.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.Yellow))
                background = "yellow" } else { resetColors() }
        }


        binding.cvBgcPurple.setOnClickListener {
            if (background != "purple"){
                resetColors()
                binding.cvBgcPurple.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.Purple))
                background = "purple" } else { resetColors() }
        }

        binding.cvBgcRed.setOnClickListener {
            if (background != "red"){
                resetColors()
                binding.cvBgcRed.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.Red))
                background = "red" } else { resetColors() }
        }

        binding.cvBgGreen.setOnClickListener {
            if (background != "green"){
                resetColors()
                binding.cvBgGreen.setCardBackgroundColor(ContextCompat.getColor(requireContext(),R.color.Green))
                background = "green" } else { resetColors() }
        }

        binding.spinnerSubscriptionCategory.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_dropdown_item,categories)

        binding.btnContinue.setOnClickListener {
            val getCurrentSession = firebaseRepository.checkUserSession()

            if (getCurrentSession){

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
                    loadInputData(amount,category,title, dayBiling, background, colorText)
                    dismiss()
                } else {
                    if (amount == 0.0) {
                        binding.tvErrorAmount.visibility = View.VISIBLE
                        if (title != ""){
                            binding.tvErrorTitle.visibility = View.GONE
                        }
                    }
                    if (title == ""){
                        binding.tvErrorTitle.visibility = View.VISIBLE
                        if (amount != 0.0) {
                            binding.tvErrorAmount.visibility = View.GONE
                        }
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


        return builder.create()
    }

    private fun loadInputData(amountEntered: Double, categoryEntered: String, titleEntered: String, dayBillingEntered: Int, backgroundEntered: String, colorText: String){
        firebaseSubscriptionRepo.loadSubscription(amountEntered, categoryEntered, titleEntered, dayBillingEntered, backgroundEntered, colorText )
    }
}