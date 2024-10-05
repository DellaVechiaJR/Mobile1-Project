package com.example.keijuu

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText

    companion object {
        val users = mutableListOf<Pair<String, String>>().apply {
            add(Pair("123", "123"))
        }
    }


        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        nameEditText = findViewById(R.id.nameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        confirmPasswordEditText = findViewById(R.id.confirmPasswordEditText)

        findViewById<Button>(R.id.registerButton).setOnClickListener {
            if (validateFields()) {
                // Adicionar usuário ao array
                users.add(Pair(emailEditText.text.toString(), passwordEditText.text.toString()))
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    private fun validateFields(): Boolean {
        if (nameEditText.text.isEmpty() || emailEditText.text.isEmpty() ||
            passwordEditText.text.isEmpty() || confirmPasswordEditText.text.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()) {
            Toast.makeText(this, "E-mail inválido.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (passwordEditText.text.toString() != confirmPasswordEditText.text.toString()) {
            Toast.makeText(this, "As senhas não coincidem.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
