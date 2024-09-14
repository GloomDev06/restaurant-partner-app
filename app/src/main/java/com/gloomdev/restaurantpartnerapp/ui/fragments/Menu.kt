package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.adapters.MenuItemAdapter
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding
import com.gloomdev.restaurantpartnerapp.databinding.FragmentMenuBinding
import com.gloomdev.restaurantpartnerapp.models.AllMenu
import com.gloomdev.restaurantpartnerapp.ui.activities.AddMenuActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Menu : Fragment() {
    private lateinit var databaseReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private var menuItems : ArrayList<AllMenu> = ArrayList()
    private lateinit var binding: FragmentMenuBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addMenu.setOnClickListener {
            val intent = Intent(requireContext(), AddMenuActivity::class.java)
            startActivity(intent)
        }

        databaseReference = FirebaseDatabase.getInstance().reference
        retrieveMenuItem()
    }

    private fun retrieveMenuItem() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        database = FirebaseDatabase.getInstance()
        val foodRef: DatabaseReference = database.reference.child("menu").child(userId.toString())
        foodRef.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                menuItems.clear()
                for (foodSnapshot in snapshot.children) {
                    val menuItem = foodSnapshot.getValue(AllMenu::class.java)
                    menuItem?.let {
                        menuItems.add(it)
                    }
                }
                setAdapter()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("DatabaseError", "onCancelled: ${error.message}")
            }
        })
    }

    private fun setAdapter() {
        val adapter = MenuItemAdapter(requireContext(), menuItems, databaseReference) { position ->
            deleteMenuItems(position)
        }
        binding.menuRV.layoutManager = LinearLayoutManager(requireContext())
        binding.menuRV.adapter = adapter
    }

    private fun deleteMenuItems(position: Int) {
        val userId = FirebaseAuth.getInstance().currentUser?.uid
        val menuItemToDelete = menuItems[position]
        val menuItemKey = menuItemToDelete.key
        val menuRef = database.reference.child("menu").child(userId.toString()).child(menuItemKey!!)
        menuRef.removeValue().addOnCompleteListener { task ->
            if(task.isSuccessful) {
                menuItems.removeAt(position)
                binding.menuRV.adapter?.notifyItemRemoved(position)
                Toast.makeText(requireContext(), "Item was deleted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Item couldn't be deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}