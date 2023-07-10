package com.example.smartbudget.ui.views.fragments.profile

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.smartbudget.R
import com.example.smartbudget.databinding.FragmentProfileBinding
import com.example.smartbudget.ui.views.contracts.FragmentContract
import com.google.android.material.bottomsheet.BottomSheetDialog


class Profile : Fragment(), FragmentContract {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.llSettingSecurity.setOnClickListener {
            showBottomSheetDialog(R.layout.bs_profilesecuritysettings)
        }

        binding.llSettingsInfo.setOnClickListener {
            showBottomSheetDialog(R.layout.bs_profileinfosettings)
        }

        binding.llSettingsSessions.setOnClickListener {
            showBottomSheetDialog(R.layout.bs_profilesessionsettings)
        }
    }

    override fun display() {
        TODO("Not yet implemented")
    }

    private fun showBottomSheetDialog(layoutResId: Int) {
        val bottomSheetDialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(layoutResId, null)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }
}