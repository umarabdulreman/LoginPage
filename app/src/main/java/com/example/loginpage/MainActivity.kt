package com.example.loginpage

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import com.google.firebase.auth.FirebaseAuth
import com.example.loginpage.databinding.ExploringBinding // Update the package name and class name for your actual binding class

class MainActivity : ComponentActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ExploringBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ExploringBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        // Handle login button click
        binding.button4.setOnClickListener {
            val email = binding.editTextTextEmailAddress2.text.toString().trim()
            val password = binding.editTextTextPassword2.text.toString().trim()

            // Check if email and password fields are not empty
            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Call the login function
                performLogin(email, password)
            } else {
                // Show an error message if any of the fields are empty
                Toast.makeText(this, "Please enter email and password.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun performLogin(email: String, password: String) {
        // Use Firebase Auth to sign in the user
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Login successful, handle what to do next (e.g., navigate to the main activity)
                    Toast.makeText(this, "Login successful.", Toast.LENGTH_SHORT).show()
                    // Add your code here for the next steps after successful login
                } else {
                    // Login failed, show an error message
                    Toast.makeText(this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
