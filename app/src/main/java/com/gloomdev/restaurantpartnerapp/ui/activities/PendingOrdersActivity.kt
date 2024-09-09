package com.gloomdev.restaurantpartnerapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.DeliveryAdapter
import com.gloomdev.restaurantpartnerapp.adapters.PendingOrderAdapter
import com.gloomdev.restaurantpartnerapp.databinding.ActivityPendingOrdersBinding

class PendingOrdersActivity : AppCompatActivity() {
    private val binding : ActivityPendingOrdersBinding by lazy {
        ActivityPendingOrdersBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

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

        val adapter = PendingOrderAdapter(orderedCustomerNames, orderedQuantity, foodImage, this)
        binding.pendingOrdersRV.adapter = adapter
        binding.pendingOrdersRV.layoutManager = LinearLayoutManager(this)
        binding.backbutton.setOnClickListener {
            finish()
        }
    }
}