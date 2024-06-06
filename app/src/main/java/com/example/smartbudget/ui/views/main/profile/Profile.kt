package com.example.smartbudget.ui.views.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.smartbudget.databinding.FragmentProfileBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.example.smartbudget.ui.views.main.profile.bottomsheets.ProfileSecuritySettings
import com.example.smartbudget.ui.views.main.profile.bottomsheets.ProfileSessionSettings
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class Profile : Fragment(), FragmentContract {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.llSettingSecurity.setOnClickListener {
            showBottomSheetDialog(ProfileSecuritySettings::class.java)
        }

        binding.llSettingsInfo.setOnClickListener {
//            showBottomSheetDialog(R.layout.bs_profileinfosettings)
        }

        binding.llSettingsSessions.setOnClickListener {
            showBottomSheetDialog(ProfileSessionSettings::class.java)
        }
    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private fun <T : BottomSheetDialogFragment> showBottomSheetDialog(fragmentClass: Class<T>) {
        val fragment = fragmentClass.getDeclaredConstructor().newInstance()
        fragment.show(parentFragmentManager, fragment.tag)
    }
}