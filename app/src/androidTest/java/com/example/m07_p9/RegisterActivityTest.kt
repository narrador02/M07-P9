package com.example.m07_p9

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.Matchers.allOf
import org.junit.Rule
import org.junit.Test
import org.hamcrest.Matchers.not
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RegisterActivityTest {

    @get:Rule
    val rule = ActivityScenarioRule(RegisterActivity::class.java)

    @Test
    fun registre_exitós_no_te_errors() {
        onView(withId(R.id.edit_username)).perform(typeText("cris"), closeSoftKeyboard())
        onView(withId(R.id.edit_email)).perform(typeText("cris@ejemplo.com"), closeSoftKeyboard())
        onView(withId(R.id.edit_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.edit_confirm_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.btn_register)).perform(click())

        Thread.sleep(500)

        onView(withId(R.id.edit_username)).check(matches(not(hasErrorText("El nom és obligatori"))))
        onView(withId(R.id.edit_email)).check(matches(not(hasErrorText("Correu no vàlid"))))
        onView(withId(R.id.edit_password)).check(matches(not(hasErrorText("Ha de tenir mínim 8 caràcters"))))
        onView(withId(R.id.edit_confirm_password)).check(matches(not(hasErrorText("Cal confirmar la contrasenya"))))
    }

    @Test
    fun camps_buits_mostren_errors() {
        onView(withId(R.id.btn_register)).perform(click())
        onView(withId(R.id.edit_username)).check(matches(hasErrorText("El nom és obligatori")))
        onView(withId(R.id.edit_email)).check(matches(hasErrorText("Correu no vàlid")))
        onView(withId(R.id.edit_password)).check(matches(hasErrorText("Ha de tenir mínim 8 caràcters")))
        onView(withId(R.id.edit_confirm_password)).check(matches(hasErrorText("Cal confirmar la contrasenya")))
    }

    @Test
    fun email_invalid_mostra_error() {
        onView(withId(R.id.edit_username)).perform(typeText("cris"), closeSoftKeyboard())
        onView(withId(R.id.edit_email)).perform(typeText("correo.com"), closeSoftKeyboard())
        onView(withId(R.id.edit_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.edit_confirm_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.btn_register)).perform(click())

        onView(withId(R.id.edit_email)).check(matches(hasErrorText("Correu no vàlid")))
    }

    @Test
    fun contrasenyes_no_coincideixen_mostra_error() {
        onView(withId(R.id.edit_username)).perform(typeText("cris"), closeSoftKeyboard())
        onView(withId(R.id.edit_email)).perform(typeText("cris@mail.com"), closeSoftKeyboard())
        onView(withId(R.id.edit_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.edit_confirm_password)).perform(typeText("Password2"), closeSoftKeyboard())
        onView(withId(R.id.btn_register)).perform(click())

        onView(withId(R.id.edit_confirm_password)).check(matches(hasErrorText("No coincideixen")))
    }

    @Test
    fun registre_amb_espais_registra_correctament() {
        onView(withId(R.id.edit_username)).perform(typeText("   cris   "), closeSoftKeyboard())
        onView(withId(R.id.edit_email)).perform(typeText("cris@e.com"), closeSoftKeyboard())
        onView(withId(R.id.edit_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.edit_confirm_password)).perform(typeText("Password1"), closeSoftKeyboard())
        onView(withId(R.id.btn_register)).perform(click())

        // Espera a que se procese
        Thread.sleep(500)

        // Verifica que NO hay errores en los campos tras registro
        onView(withId(R.id.edit_username)).check(matches(not(hasErrorText("El nom és obligatori"))))
        onView(withId(R.id.edit_email)).check(matches(not(hasErrorText("Correu no vàlid"))))
        onView(withId(R.id.edit_password)).check(matches(not(hasErrorText("Ha de tenir mínim 8 caràcters"))))
        onView(withId(R.id.edit_confirm_password)).check(matches(not(hasErrorText("No coincideixen"))))
    }

    class ToastMatcher : org.hamcrest.TypeSafeMatcher<androidx.test.espresso.Root>() {
        override fun describeTo(description: org.hamcrest.Description) {
            description.appendText("is toast")
        }

        override fun matchesSafely(root: androidx.test.espresso.Root): Boolean {
            val type = root.windowLayoutParams?.get()?.type
            if (type == android.view.WindowManager.LayoutParams.TYPE_TOAST) {
                val windowToken = root.decorView.windowToken
                val appToken = root.decorView.applicationWindowToken
                return windowToken === appToken
            }
            return false
        }
    }
}