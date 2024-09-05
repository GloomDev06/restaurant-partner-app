package com.gloomdev.restaurantpartnerapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gloomdev.restaurantpartnerapp.databinding.ItemItemsBinding

class AddItemAdapter(private val menuItemName: ArrayList<String>, private val menuItemPrice: ArrayList<String>, private val menuItemImage: ArrayList<Int>): RecyclerView.Adapter<AddItemAdapter.AddAllItemViewHolder>() {
    private val itemQuantities = IntArray(menuItemName.size) {1}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddAllItemViewHolder {
        val binding = ItemItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AddAllItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AddAllItemViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = menuItemName.size

    inner class AddAllItemViewHolder(private val binding: ItemItemsBinding) :RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.apply {
                val quanity = itemQuantities[position]
                foodNameTextView.text = menuItemName[position]
                foodPriceTextView.text = menuItemPrice[position]
                foodImageView.setImageResource(menuItemImage[position])
                quantityTextView.text = quanity.toString()

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
            menuItemName.removeAt(position)
            menuItemPrice.removeAt(position)
            menuItemImage.removeAt(position)
            notifyItemRemoved(position)
            notifyItemRangeChanged(position, menuItemName.size)
        }

        private fun increaseQuantity(position: Int) {
            if(itemQuantities[position]<10) {
                itemQuantities[position]++
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }

        private fun decreaseQuantity(position: Int) {
            if(itemQuantities[position]>1) {
                itemQuantities[position]--
                binding.quantityTextView.text = itemQuantities[position].toString()
            }
        }

    }
}