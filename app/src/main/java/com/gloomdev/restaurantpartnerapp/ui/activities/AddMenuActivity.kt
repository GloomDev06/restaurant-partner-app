package com.gloomdev.restaurantpartnerapp.ui.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gloomdev.restaurantpartnerapp.R
import com.gloomdev.restaurantpartnerapp.databinding.ActivityAddMenuBinding

class AddMenuActivity : AppCompatActivity() {
    private val binding:ActivityAddMenuBinding by lazy {
        ActivityAddMenuBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.selectImage.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.backbutton.setOnClickListener {
            finish()
        }
    }
    private val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri->
        if(uri!=null) {
            binding.selectedImage.setImageURI(uri)
        }
    }
}