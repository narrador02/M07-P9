package com.example.m07_p9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {

    private val _errorNom = MutableLiveData("")
    val errorNom: LiveData<String> = _errorNom

    private val _errorEmail = MutableLiveData("")
    val errorEmail: LiveData<String> = _errorEmail

    private val _errorPassword = MutableLiveData("")
    val errorPassword: LiveData<String> = _errorPassword

    private val _errorConfirmacio = MutableLiveData("")
    val errorConfirmacio: LiveData<String> = _errorConfirmacio

    fun validar(nom: String, email: String, password: String, confirmacio: String) {
        _errorNom.value = validarNom(nom)
        _errorEmail.value = validarEmail(email)
        _errorPassword.value = validarPassword(password)
        _errorConfirmacio.value = validarConfirmacio(password, confirmacio)
    }

    private fun validarNom(nom: String): String {
        if (nom.trim().isEmpty()) return "El nom és obligatori"
        if (!nom.matches(Regex("^[a-zA-ZÀ-ÿ\\s]+$"))) return "No es permeten símbols no alfabètics"
        return ""
    }

    private fun validarEmail(email: String): String {
        if (!email.matches(Regex("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$"))) return "Correu no vàlid"
        return ""
    }

    private fun validarPassword(pass: String): String {
        if (pass.length < 8) return "Ha de tenir mínim 8 caràcters"
        if (!Regex("[A-Z]").containsMatchIn(pass)) return "Ha de contenir una majúscula"
        if (!Regex("\\d").containsMatchIn(pass)) return "Ha de contenir almenys un número"
        if (pass.contains(" ")) return "No es permeten espais"
        return ""
    }

    private fun validarConfirmacio(pass: String, conf: String): String {
        return if (pass != conf) "No coincideixen" else ""
    }
}
