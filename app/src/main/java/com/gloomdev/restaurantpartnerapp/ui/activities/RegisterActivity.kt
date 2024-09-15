package com.gloomdev.restaurantpartnerapp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gloomdev.restaurantpartnerapp.databinding.ActivityRegisterBinding
import com.gloomdev.restaurantpartnerapp.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase


class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var username: String
    private lateinit var nameOfRestaurant: String
    private lateinit var location: String
    private lateinit var phoneNumber: String
    private lateinit var database: DatabaseReference

    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //Removing Action Bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        auth = Firebase.auth
        database = Firebase.database.reference

        binding.registerButton.setOnClickListener {
            username = binding.nameOfOwner.text.toString().trim()
            nameOfRestaurant = binding.nameOfRestaurant.text.toString().trim()
            email = binding.email.text.toString().trim()
            password = binding.password.text.toString().trim()
            location = binding.listOfLocation.text.toString().trim()
            phoneNumber = "+91" + binding.phone.text.toString().trim()

            if(username.isBlank() || nameOfRestaurant.isBlank() || email.isBlank() || password.isBlank() || location.isBlank()) {
                Toast.makeText(this, "Fill all credentials", Toast.LENGTH_SHORT).show()
            } else {
                createAccount(username, nameOfRestaurant, email, phoneNumber, location, password)
            }
        }

        binding.loginToExistingAccount.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        val locationList = arrayOf("Bangalore", "Delhi", "Mumbai", "Kolkata", "Chennai")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, locationList)
        binding.listOfLocation.setAdapter(adapter)
    }

    // creates a new account
    private fun createAccount(
        username: String,
        nameOfRestaurant: String,
        email: String,
        phoneNumber: String,
        location: String,
        password: String
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if(task.isSuccessful) {
                saveUserData(username, nameOfRestaurant, email, phoneNumber, location)
                Toast.makeText(this, "Account created successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Account creation failed", Toast.LENGTH_SHORT).show()
                Log.d("Account", "createAccount: Failure", task.exception)
            }
        }
    }

    // saves user data in database
    private fun saveUserData(
        username: String,
        nameOfRestaurant: String,
        email: String,
        phoneNumber: String,
        location: String
    ) {
        val user = UserModel(username, nameOfRestaurant, email, phoneNumber, location)
        val userId = FirebaseAuth.getInstance().currentUser!!.uid
        database.child("user").child(userId).setValue(user)
    }
}