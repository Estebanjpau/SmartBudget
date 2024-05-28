package com.example.smartbudget.ui.views.main.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartbudget.databinding.ProfileSessionSettingsBinding
import com.example.smartbudget.ui.utils.popup.SnackbarUtils
import com.example.smartbudget.ui.views.main.activity.MainActivity
import com.example.smartbudget.viewmodel.auth.AuthViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileSessionSettings : BottomSheetDialogFragment() {

    lateinit var binding: ProfileSessionSettingsBinding

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ProfileSessionSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvLogout.setOnClickListener {
            authViewModel.logout()
        }

        authViewModel.authResultFirebase.logoutResult.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                val intent = Intent(requireContext(), MainActivity::class.java)
                requireActivity().finish()

                startActivity(intent)
            } else {
                   SnackbarUtils.showCustomSnackbar(binding.root, "Invalid Logout")
            }
        })

    }
}