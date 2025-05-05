
package com.example.m07_p9

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.m07_p9.RegisterViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RegisterViewModelTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: RegisterViewModel

    @Before
    fun setUp() {
        viewModel = RegisterViewModel()
    }

    @Test
    fun `nom buit dona error`() {
        viewModel.validar("", "a@a.com", "12345678", "12345678")
        assertEquals("El nom és obligatori", viewModel.errorNom.value)
    }

    @Test
    fun `email invalid dona error`() {
        viewModel.validar("cris", "correo.com", "12345678", "12345678")
        assertEquals("Correu no vàlid", viewModel.errorEmail.value)
    }

    @Test
    fun `password curta dona error`() {
        viewModel.validar("cris", "a@a.com", "123", "123")
        assertEquals("Ha de tenir mínim 8 caràcters", viewModel.errorPassword.value)
    }

    @Test
    fun `confirmacio no coincideix dona error`() {
        viewModel.validar("cris", "a@a.com", "12345678", "87654321")
        assertEquals("No coincideixen", viewModel.errorConfirmacio.value)
    }

    @Test
    fun `nom amb emojis dona error`() {
        viewModel.validar("😃cris", "cris@mail.com", "12345678", "12345678")
        assertEquals("No es permeten símbols no alfabètics", viewModel.errorNom.value)
    }

    @Test
    fun `password sense numero dona error`() {
        viewModel.validar("cris", "cris@mail.com", "Password", "Password")
        assertEquals("Ha de contenir almenys un número", viewModel.errorPassword.value)
    }

    @Test
    fun `password sense majuscula dona error`() {
        viewModel.validar("cris", "cris@mail.com", "password1", "password1")
        assertEquals("Ha de contenir una majúscula", viewModel.errorPassword.value)
    }

    @Test
    fun `password amb espais dona error`() {
        viewModel.validar("cris", "cris@mail.com", "1234 5678", "1234 5678")
        assertEquals("No es permeten espais", viewModel.errorPassword.value)
    }

    @Test
    fun `validacio correcta retorna cap error`() {
        viewModel.validar("cris", "cris@mail.com", "Password1", "Password1")
        assertEquals("", viewModel.errorNom.value)
        assertEquals("", viewModel.errorEmail.value)
        assertEquals("", viewModel.errorPassword.value)
        assertEquals("", viewModel.errorConfirmacio.value)
    }
}
