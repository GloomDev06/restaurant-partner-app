package com.gloomdev.restaurantpartnerapp.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gloomdev.restaurantpartnerapp.databinding.ItemItemsBinding
import com.gloomdev.restaurantpartnerapp.models.AllMenu
import com.google.firebase.database.DatabaseReference

class MenuItemAdapter(
    private val context: Context,
    private val menuList: ArrayList<AllMenu>,
    databaseReference: DatabaseReference
) : RecyclerView.Adapter<MenuItemAdapter.AddAllItemViewHolder>() {
    private val itemQuantities = IntArray(menuList.size) { 1 }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddAllItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuList.size

    inner class AddAllItemViewHolder(private val binding: ItemItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            binding.apply {
                val quantity = itemQuantities[position]
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)

                foodNameTextView.text = menuItem.foodName
                foodPriceTextView.text = "₹ "+ menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)
                quantityTextView.text = quantity.toString()

                minusBtn.setOnClickListener {
                    decreaseQuantity(position)
                }

                plusBtn.setOnClickListener {
                    increaseQuantity(position)
                }

                deleteBtn.setOnClickListener {
                    deleteQuantity(position)
                }
            }
        }

        private fun deleteQuantity(position: Int) {
            menuList.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, menuList.size)
        }

        private fun increaseQuantity(position: Int) {
            if (itemQuantities[position] < 10) {
                itemQuantities[position]++
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if (itemQuantities[position] > 1) {
                itemQuantities[position]--
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }

    }
}