package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding
import com.gloomdev.restaurantpartnerapp.databinding.FragmentProfileBinding

class Profile : Fragment() {
    private lateinit var binding : FragmentProfileBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            name.isEnabled = false
            address.isEnabled = false
            email.isEnabled = false
            phone.isEnabled = false
            password.isEnabled = false

            var isEnable = false
            editButton.setOnClickListener {
                isEnable = !isEnable

                name.isEnabled = isEnable
                address.isEnabled = isEnable
                email.isEnabled = isEnable
                phone.isEnabled = isEnable
                password.isEnabled = isEnable

                if(isEnable) {
                    name.requestFocus()
                }
            }
        }
    }
}