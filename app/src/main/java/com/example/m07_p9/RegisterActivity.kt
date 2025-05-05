
package com.example.m07_p9

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var editUsername: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText
    private lateinit var editConfirmPassword: EditText

    private val viewModel: RegisterViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        editUsername = findViewById(R.id.edit_username)
        editEmail = findViewById(R.id.edit_email)
        editPassword = findViewById(R.id.edit_password)
        editConfirmPassword = findViewById(R.id.edit_confirm_password)
        val btnRegister = findViewById<Button>(R.id.btn_register)

        viewModel.errorNom.observe(this) {
            if (it.isNotEmpty()) editUsername.error = it
        }

        viewModel.errorEmail.observe(this) {
            if (it.isNotEmpty()) editEmail.error = it
        }

        viewModel.errorPassword.observe(this) {
            if (it.isNotEmpty()) editPassword.error = it
        }

        viewModel.errorConfirmacio.observe(this) {
            if (it.isNotEmpty()) editConfirmPassword.error = it
        }

        btnRegister.setOnClickListener {
            val nom = editUsername.text.toString().trim()
            val email = editEmail.text.toString().trim()
            val password = editPassword.text.toString()
            val confirm = editConfirmPassword.text.toString()

            viewModel.validar(nom, email, password, confirm)

            if (viewModel.errorNom.value == "" &&
                viewModel.errorEmail.value == "" &&
                viewModel.errorPassword.value == "" &&
                viewModel.errorConfirmacio.value == "") {
                Toast.makeText(this, "Usuari registrat correctament", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
