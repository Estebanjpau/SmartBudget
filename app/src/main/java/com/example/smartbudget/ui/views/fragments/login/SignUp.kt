package com.example.smartbudget.ui.views.fragments.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartbudget.databinding.FragmentSignupBinding
import com.example.smartbudget.viewmodel.auth.AuthViewModel

class SignUp : Fragment() {

    companion object {
        fun newInstance() = SignUp()
    }

    private val viewModel: SignUpViewModel by viewModels()

    private val authViewModel: AuthViewModel by viewModels()

    private lateinit var binding: FragmentSignupBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.tvSignin.setOnClickListener {
            viewModel.navigateToSignInScreen(requireActivity())
        }

        binding.SignUpButton.setOnClickListener {
            val nickname = binding.etLoginUsername.text.toString()
            val username = binding.etLoginUsername.text.toString()
            val password = binding.etLoginPassword.text.toString()
            val confirmPassword = binding.etLoginPassword.text.toString()
            authViewModel.register(nickname, username)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}