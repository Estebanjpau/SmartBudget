package com.example.smartbudget.ui.views.fragments.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartbudget.databinding.FragmentSigninBinding
import com.example.smartbudget.ui.views.activities.MainActivity
import com.example.smartbudget.viewmodel.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignIn : Fragment() {

    private val viewModel: SignInViewModel by viewModels()

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var binding: FragmentSigninBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSigninBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val username = binding.etLoginUsername.text.toString()
            val password = binding.etLoginPassword.text.toString()
            authViewModel.login(username, password)
        }

        binding.tvSignup.setOnClickListener {
            viewModel.navigateToSignUpScreen(requireActivity())
        }

        authViewModel.authResultFirebase.loginResult.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                binding.tvErrorLogin.visibility = View.GONE
                navigateToHomeScreen()
            } else {
                binding.tvErrorLogin.visibility = View.VISIBLE
            }
        })
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().finish()

        startActivity(intent)
    }
}