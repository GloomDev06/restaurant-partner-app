package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.PendingOrderAdapter
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding

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

        val orderedCustomerNames = arrayListOf(
            "John Doe",
            "Jane Smith",
            "Bob Johnson",
            "Alice Brown"
        )

        val orderedQuantity = arrayListOf(
            "5",
            "3",
            "8",
            "2"
        )

        val foodImage = arrayListOf(
            R.drawable.sample_food,
            R.drawable.sample_food,
            R.drawable.sample_food,
            R.drawable.sample_food
        )

        val adapter = PendingOrderAdapter(orderedCustomerNames, orderedQuantity, foodImage, requireContext())
        binding.pendingOrdersRV.adapter = adapter
        binding.pendingOrdersRV.layoutManager = LinearLayoutManager(requireContext())
    }
}