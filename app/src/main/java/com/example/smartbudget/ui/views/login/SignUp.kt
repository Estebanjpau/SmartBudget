package com.example.smartbudget.ui.views.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentSignupBinding
import com.example.smartbudget.ui.views.main.activity.MainActivity
import com.example.smartbudget.ui.views.auth.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUp : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUp_to_signIn)
        }

        binding.etNickname.addTextChangedListener { text ->
            viewModel.setNickname(text.toString())
        }

        binding.etRegisterUsername.addTextChangedListener { text ->
            viewModel.setEmail(text.toString())
        }

        binding.etRegisterPassword.addTextChangedListener { text ->
            viewModel.setPassword(text.toString())
        }

        binding.etConfirmPassword.addTextChangedListener { text ->
            viewModel.setConfirmPassword(text.toString())
        }

        viewModel.isFormValid.observe(viewLifecycleOwner) { isValid ->
            binding.SignUpButton.isEnabled = isValid
        }

        fun toggleVisibility(textView: TextView) {
            textView.visibility = if (textView.visibility == View.GONE) {
                binding.tvInfoNickname.visibility = View.GONE
                binding.tvInfoEmail.visibility = View.GONE
                binding.tvInfoPassword.visibility = View.GONE
                binding.tvInfoConfirmPassword.visibility = View.GONE
                View.VISIBLE
            } else View.GONE
        }

        binding.ibNicknameInfo.setOnClickListener {
            toggleVisibility(binding.tvInfoNickname)
        }

        binding.ibEmailInfo.setOnClickListener {
            toggleVisibility(binding.tvInfoEmail)
        }

        binding.ibPasswordInfo.setOnClickListener {
            toggleVisibility(binding.tvInfoPassword)
        }

        binding.ibConfirmPasswordInfo.setOnClickListener {
            toggleVisibility(binding.tvInfoConfirmPassword)
        }

        authViewModel.authResultFirebase.registerResult.observe(viewLifecycleOwner, Observer { result ->
            if (result.isSuccess) {
                navigateToHomeScreen()
            } else {
                Toast.makeText(requireContext(), result.isFailure.toString(), Toast.LENGTH_SHORT).show()
            }
        })

        binding.SignUpButton.setOnClickListener {
            val nickname = binding.etNickname.text.toString()
            val email = binding.etRegisterUsername.text.toString()
            val password = binding.etRegisterPassword.text.toString()
            authViewModel.register(email, password, nickname)
        }
    }

    private fun navigateToHomeScreen() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        requireActivity().finish()

        startActivity(intent)
    }
}