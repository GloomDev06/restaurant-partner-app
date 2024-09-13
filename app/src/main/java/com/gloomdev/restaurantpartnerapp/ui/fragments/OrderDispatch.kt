package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.adapters.DeliveryAdapter
import com.gloomdev.restaurantpartnerapp.databinding.FragmentOrderDispatchBinding

class OrderDispatch : Fragment() {
    private lateinit var binding: FragmentOrderDispatchBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDispatchBinding.inflate(layoutInflater, container, false)
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