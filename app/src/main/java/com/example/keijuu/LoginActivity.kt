package com.example.keijuu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        errorTextView = findViewById(R.id.errorTextView)

        findViewById<Button>(R.id.loginButton).setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateLogin(email, password)) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                errorTextView.text = "Login ou senha inválida"
                errorTextView.visibility = TextView.VISIBLE
            }
        }

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun validateLogin(email: String, password: String): Boolean {
        return RegisterActivity.users.any { it.first == email && it.second == password }
    }
}
