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
    databaseReference: DatabaseReference,
    private val onDeleteClickListener: (position: Int) -> Unit
) : RecyclerView.Adapter<MenuItemAdapter.AddAllItemViewHolder>() {
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
                val menuItem = menuList[position]
                val uriString = menuItem.foodImage
                val uri = Uri.parse(uriString)

                foodNameTextView.text = menuItem.foodName
                foodPriceTextView.text = "₹ "+ menuItem.foodPrice
                Glide.with(context).load(uri).into(foodImageView)

                deleteBtn.setOnClickListener {
                    onDeleteClickListener(position)
                }
            }
        }
    }
}