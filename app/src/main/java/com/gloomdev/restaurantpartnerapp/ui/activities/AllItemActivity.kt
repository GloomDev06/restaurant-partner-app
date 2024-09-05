package com.gloomdev.restaurantpartnerapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.AddItemAdapter
import com.gloomdev.restaurantpartnerapp.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private val binding: ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
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

        binding.backbutton.setOnClickListener {
            finish()
        }

        val menuFoodName = listOf("Burger", "Pizza", "Pasta", "Salad", "Ice Cream")
        val menuFoodPrice = listOf("$5", "$10", "$15", "$20", "$25")
        val menuFoodImage = listOf(R.drawable.sample_food, R.drawable.sample_food, R.drawable.sample_food, R.drawable.sample_food, R.drawable.sample_food)

        val adapter = AddItemAdapter(ArrayList(menuFoodName), ArrayList(menuFoodPrice), ArrayList(menuFoodImage))
        binding.menuRV.layoutManager = LinearLayoutManager(this)
        binding.menuRV.adapter = adapter
    }
}