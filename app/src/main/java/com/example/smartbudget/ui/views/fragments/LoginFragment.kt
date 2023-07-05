package com.example.smartbudget.ui.views.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.smartbudget.databinding.FragmentLoginBinding
import com.example.smartbudget.ui.views.activities.MainActivity
import com.example.smartbudget.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()
    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginButton.setOnClickListener {
            val username = binding.etLoginUsername.text.toString()
            val password = binding.etLoginPassword.text.toString()
            viewModel.loginUser(username, password)
        }

        viewModel.loadingState.observe(viewLifecycleOwner, Observer { isLoading ->
            // Mostrar u ocultar indicador de carga según el valor de isLoading
            if (isLoading) {
                // Mostrar indicador de carga
            } else {
                // Ocultar indicador de carga
                if(viewModel.navigateToHomeScreen.value != null){
                    val intent = Intent(requireContext(), MainActivity::class.java)
                    requireActivity().finish()

                    startActivity(intent)
                }
            }
        })

        viewModel.errorMessage.observe(viewLifecycleOwner, Observer { errorMessage ->
            // Mostrar el mensaje de error en la vista
        })

        viewModel.navigateToHomeScreen.observe(viewLifecycleOwner, Observer {
            // Navegar a la pantalla principal
        })
    }
}