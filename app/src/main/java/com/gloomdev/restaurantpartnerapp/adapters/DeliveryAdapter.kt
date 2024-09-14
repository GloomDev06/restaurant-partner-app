package com.gloomdev.restaurantpartnerapp.adapters

import android.content.res.ColorStateList
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gloomdev.restaurantpartnerapp.databinding.ItemDeliveryBinding

class DeliveryAdapter(private val customerNames: MutableList<String>, private val moneyStatus: MutableList<Boolean>) : RecyclerView.Adapter<DeliveryAdapter.DeliveryViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveryViewHolder {
        val binding = ItemDeliveryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeliveryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeliveryViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = customerNames.size

    inner class DeliveryViewHolder(private val binding: ItemDeliveryBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                customerName.text = customerNames[position]
                if (moneyStatus[position]) {
                    statusMoney.text = "Received"
                } else {
                    statusMoney.text = "Not Yet Received"
                }

                val colorMap = mapOf(
                    false to Color.RED,
                    true to Color.GREEN
                )

                statusMoney.setTextColor(colorMap[moneyStatus[position]]?: Color.BLACK)
                statusColor.backgroundTintList = ColorStateList.valueOf(colorMap[moneyStatus[position]]?: Color.BLACK)
            }
        }

    }
}