package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding
import com.gloomdev.restaurantpartnerapp.ui.activities.AddMenuActivity
import com.gloomdev.restaurantpartnerapp.ui.activities.AllItemActivity
import com.gloomdev.restaurantpartnerapp.ui.activities.OutForDeliveryActivity
import com.gloomdev.restaurantpartnerapp.ui.activities.PendingOrdersActivity

class Home : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMenu.setOnClickListener {
            val intent = Intent(requireContext(), AddMenuActivity::class.java)
            startActivity(intent)
        }

        binding.allItemMenu.setOnClickListener{
            val intent = Intent(requireContext(), AllItemActivity::class.java)
            startActivity(intent)
        }

        binding.outForDelivery.setOnClickListener{
            val intent = Intent(requireContext(), OutForDeliveryActivity::class.java)
            startActivity(intent)
        }

        binding.pendingOrders.setOnClickListener{
            val intent = Intent(requireContext(), PendingOrdersActivity::class.java)
            startActivity(intent)
        }
    }
}