package com.example.m07_p9

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val btnRegister: Button = findViewById(R.id.btn_register)

        btnRegister.setOnClickListener {
            Toast.makeText(this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show()
        }
    }
}