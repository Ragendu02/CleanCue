package com.mad.cleancue

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Find views
        val etEmail = findViewById<TextInputEditText>(R.id.etEmail)
        val etPassword = findViewById<TextInputEditText>(R.id.etPassword)
        val btnLogin = findViewById<MaterialButton>(R.id.btnLogin)
        val tvRegisterLink = findViewById<TextView>(R.id.tvRegisterLink)

        // Handle login button click
        btnLogin.setOnClickListener {
            val email = etEmail.text.toString().trim()
            val password = etPassword.text.toString().trim()

            // Simple validation
            if (email.isEmpty()) {
                etEmail.error = "Please enter email"
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                etPassword.error = "Please enter password"
                return@setOnClickListener
            }

            // Show success message
            Toast.makeText(this, "Logged in as $email", Toast.LENGTH_SHORT).show()

            // ✅ Navigate to DashboardActivity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }

        // Navigate to RegisterActivity
        tvRegisterLink.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}
