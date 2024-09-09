package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.DeliveryAdapter
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding
import com.gloomdev.restaurantpartnerapp.databinding.FragmentPastOrdersBinding

class PastOrders : Fragment() {
    private lateinit var binding: FragmentPastOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPastOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val customerNames = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Bob Johnson",
            "Alice Brown"
        )

        val moneyStatus = arrayListOf(
            "Not Yet Received",
            "Received",
            "Pending",
            "Received"
        )

        val adapter = DeliveryAdapter(customerNames, moneyStatus)
        binding.deliveryRV.adapter = adapter
        binding.deliveryRV.layoutManager = LinearLayoutManager(requireContext())
    }
}