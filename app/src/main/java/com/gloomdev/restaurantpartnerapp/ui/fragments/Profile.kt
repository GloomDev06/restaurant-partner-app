package com.gloomdev.restaurantpartnerapp.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.databinding.FragmentHomeBinding
import com.gloomdev.restaurantpartnerapp.databinding.FragmentProfileBinding
import com.gloomdev.restaurantpartnerapp.models.UserModel
import com.gloomdev.restaurantpartnerapp.ui.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Profile : Fragment() {
    private lateinit var binding: FragmentProfileBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase
    private lateinit var adminReference: DatabaseReference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        adminReference = database.reference.child("user")

        binding.apply {
            nameOfOwner.isEnabled = false
            nameOfRestaurant.isEnabled = false
            address.isEnabled = false
            email.isEnabled = false
            phone.isEnabled = false
            saveBtn.isEnabled = false

            var isEnable = false
            editButton.setOnClickListener {
                isEnable = !isEnable

                nameOfOwner.isEnabled = isEnable
                nameOfRestaurant.isEnabled = isEnable
                address.isEnabled = isEnable
//                email.isEnabled = isEnable
                phone.isEnabled = isEnable

                if (isEnable) {
                    nameOfOwner.requestFocus()
                }
                saveBtn.isEnabled = isEnable
            }

            saveBtn.setOnClickListener {
                updateUserData()
            }

            logoutBtn.setOnClickListener {
                auth.signOut()
                val intent = Intent(requireContext(), LoginActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        retrieveData()
    }

    private fun updateUserData() {
        val updatedNameOfOwner = binding.nameOfOwner.text.toString()
        val updatedNameOfRestaurant = binding.nameOfRestaurant.text.toString()
        val updatedEmail = binding.email.text.toString()
        val updatedPhone = binding.phone.text.toString()
        val updatedAddress = binding.address.text.toString()
        val userData = UserModel(
            updatedNameOfOwner,
            updatedNameOfRestaurant,
            updatedEmail,
            updatedPhone,
            updatedAddress
        )
        adminReference.child(auth.currentUser?.uid.toString()).setValue(userData).addOnSuccessListener {
            Toast.makeText(requireContext(), "Profile updated successfully", Toast.LENGTH_SHORT)
                .show()
//            auth.currentUser?.updateEmail(updatedEmail)
        }
            .addOnFailureListener {
                Toast.makeText(requireContext(), "Failed to update profile", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun retrieveData() {
        val userId = auth.currentUser?.uid
        if (userId != null) {
            val userReference = adminReference.child(userId)
            userReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val nameOfOwner = snapshot.child("username").value.toString()
                        val nameOfRestaurant = snapshot.child("nameOfRestaurant").value.toString()
                        val address = snapshot.child("location").value.toString()
                        val email = snapshot.child("email").value.toString()
                        val phone = snapshot.child("phone").value.toString()
                        setDataToTextView(nameOfOwner, nameOfRestaurant, address, email, phone)
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    private fun setDataToTextView(
        nameOfOwner: String,
        nameOfRestaurant: String,
        address: String,
        email: String,
        phone: String
    ) {
        binding.apply {
            this.nameOfOwner.setText(nameOfOwner)
            this.nameOfRestaurant.setText(nameOfRestaurant)
            this.address.setText(address)
            this.email.setText(email)
            this.phone.setText(phone)
        }
    }
}