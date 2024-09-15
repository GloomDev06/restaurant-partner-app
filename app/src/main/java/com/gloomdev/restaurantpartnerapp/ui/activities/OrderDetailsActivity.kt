package com.gloomdev.restaurantpartnerapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.OrderDetailsAdapter
import com.gloomdev.restaurantpartnerapp.databinding.ActivityOrderDetailsBinding
import com.gloomdev.restaurantpartnerapp.models.OrderDetails

class OrderDetailsActivity : AppCompatActivity() {
    private val binding: ActivityOrderDetailsBinding by lazy {
        ActivityOrderDetailsBinding.inflate(layoutInflater)
    }
    private var userName: String? = null
    private var address: String? = null
    private var phone: String? = null
    private var totalPrice: String? = null
    private var foodNames: ArrayList<String> = arrayListOf()
    private var foodImages: ArrayList<String> = arrayListOf()
    private var foodQuantity: ArrayList<Int> = arrayListOf()
    private var foodPrices: ArrayList<String> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.backBtn.setOnClickListener {
            finish()
        }

        getIntentData()

    }

    private fun getIntentData() {
        val receivedOrderDetails = intent.getSerializableExtra("UserOrderDetails") as OrderDetails
        receivedOrderDetails.let { orderDetails ->
            userName = receivedOrderDetails.userName
            address = receivedOrderDetails.address
            phone = receivedOrderDetails.phoneNumber
            totalPrice = receivedOrderDetails.totalPrice
            foodNames = receivedOrderDetails.foodNames as ArrayList<String>
            foodImages = receivedOrderDetails.foodImages as ArrayList<String>
            foodQuantity = receivedOrderDetails.foodQuantities as ArrayList<Int>
            foodPrices = receivedOrderDetails.foodPrices as ArrayList<String>

            setUserDetails()
            setAdapters()
        }
    }

    private fun setAdapters() {
        binding.orderDetailsRV.layoutManager = LinearLayoutManager(this)
        binding.orderDetailsRV.adapter =
            OrderDetailsAdapter(this, foodNames, foodImages, foodQuantity, foodPrices)
    }

    private fun setUserDetails() {
        binding.name.text = userName
        binding.address.text = address
        binding.phone.text = phone
        binding.totalPrice.text = totalPrice
    }
}