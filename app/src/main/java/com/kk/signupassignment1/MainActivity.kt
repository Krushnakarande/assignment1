package com.kk.signupassignment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Adjust window insets for edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get references to UI components
        val etName = findViewById<EditText>(R.id.et_name)
        val etContact = findViewById<EditText>(R.id.et_contact)
        val etEmail = findViewById<EditText>(R.id.et_email)
        val etPassword = findViewById<EditText>(R.id.et_password)
        val etAddress = findViewById<EditText>(R.id.et_address)
        val btnSubmit = findViewById<Button>(R.id.btn_submit)

        // Set up the submit button click listener
        btnSubmit.setOnClickListener {
            val name = etName.text.toString()
            val contact = etContact.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()
            val address = etAddress.text.toString()

            // Validation logic
            if (name.isBlank()) {
                etName.error = "Name is required"
                return@setOnClickListener
            }

            if (contact.isBlank() || contact.length != 10 || !contact.matches("\\d{10}".toRegex())) {
                etContact.error = "Enter a valid 10-digit contact number"
                return@setOnClickListener
            }

            if (email.isBlank() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                etEmail.error = "Enter a valid email address"
                return@setOnClickListener
            }

            if (password.isBlank() || password.length < 6) {
                etPassword.error = "Password must be at least 6 characters"
                return@setOnClickListener
            }

            if (address.isBlank()) {
                etAddress.error = "Address is required"
                return@setOnClickListener
            }

            // Success message
            Toast.makeText(this, "Sign up successful!", Toast.LENGTH_SHORT).show()
        }
    }
}
