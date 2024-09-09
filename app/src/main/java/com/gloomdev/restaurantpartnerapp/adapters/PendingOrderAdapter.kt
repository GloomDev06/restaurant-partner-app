package com.gloomdev.restaurantpartnerapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gloomdev.restaurantpartnerapp.databinding.ItemPendingOrdersBinding

class PendingOrderAdapter(
    private val customerNames: ArrayList<String>,
    private val quantity: ArrayList<String>,
    private val foodImage: ArrayList<Int>,
    private val context: Context
) : RecyclerView.Adapter<PendingOrderAdapter.PendingOrderViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PendingOrderViewHolder {
        val binding =
            ItemPendingOrdersBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PendingOrderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PendingOrderViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = customerNames.size

    inner class PendingOrderViewHolder(private val binding: ItemPendingOrdersBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var isAccepted = false
        fun bind(position: Int) {
            binding.apply {
                customerNameTextView.text = customerNames[position]
                quantityTextView.text = quantity[position]
                Glide.with(context).load(foodImage[position]).into(orderedFoodItemImageView)

                orderAcceptBtn.apply {
                    if (!isAccepted) {
                        text = "Accept"
                    } else {
                        text = "Dispatch"
                    }
                    setOnClickListener {
                        if (!isAccepted) {
                            text = "Dispatch"
                            isAccepted = true
                            showToast("Order Accepted")
                        } else {
                            customerNames.removeAt(adapterPosition)
                            notifyItemRemoved(adapterPosition)
                            showToast("Order Dispatched")
                        }
                    }
                }
            }
        }
        private fun showToast(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }
    }
}