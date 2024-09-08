package com.gloomdev.restaurantpartnerapp.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.adapters.DeliveryAdapter
import com.gloomdev.restaurantpartnerapp.databinding.ActivityOutForDeliveryBinding

class OutForDeliveryActivity : AppCompatActivity() {
    private val binding: ActivityOutForDeliveryBinding by lazy {
        ActivityOutForDeliveryBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        binding.deliveryRV.layoutManager = LinearLayoutManager(this)
        binding.backbutton.setOnClickListener {
            finish()
        }

    }
}