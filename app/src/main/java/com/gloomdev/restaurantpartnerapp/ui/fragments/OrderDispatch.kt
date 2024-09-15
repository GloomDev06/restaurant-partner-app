package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.adapters.DeliveryAdapter
import com.gloomdev.restaurantpartnerapp.databinding.FragmentOrderDispatchBinding
import com.gloomdev.restaurantpartnerapp.models.OrderDetails
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class OrderDispatch : Fragment() {
    private lateinit var binding: FragmentOrderDispatchBinding
    private lateinit var database: FirebaseDatabase
    private var listOfCompleteOrder: ArrayList<OrderDetails> = arrayListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDispatchBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        retrieveCompleteOrderDetails()
    }

    private fun retrieveCompleteOrderDetails() {
        database = FirebaseDatabase.getInstance()
        val completeOrderReference = database.reference.child("CompletedOrder")
            .orderByChild("currentTime")
        completeOrderReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                // clear the list before adding new data
                listOfCompleteOrder.clear()
                for (orderSnapshot in snapshot.children) {
                    val completeOrder = orderSnapshot.getValue(OrderDetails::class.java)
                    completeOrder?.let {
                        listOfCompleteOrder.add(it)
                    }
                }
                // reverse the list to show the latest order first
                listOfCompleteOrder.reverse()
                setDataIntoRecyclerView()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun setDataIntoRecyclerView() {
        val customerName = mutableListOf<String>()
        val moneyStatus = mutableListOf<Boolean>()

        for (order in listOfCompleteOrder) {
            order.userName?.let { customerName.add(it) }
            moneyStatus.add(order.paymentReceived)
        }
        val adapter = DeliveryAdapter(customerName, moneyStatus)
        binding.deliveryRV.adapter = adapter
        binding.deliveryRV.layoutManager = LinearLayoutManager(requireContext())
    }
}