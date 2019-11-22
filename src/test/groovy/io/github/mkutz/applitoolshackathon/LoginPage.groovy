package io.github.mkutz.applitoolshackathon

import geb.Page
import geb.module.PasswordInput
import geb.module.TextInput

class LoginPage extends Page {

    static at = { $('.auth-header') }

    static content = {
        usernameInput { $('#username').module(TextInput) }
        passwordInput { $('#password').module(PasswordInput) }
        submitButton { $('#log-in') }
        alert(required: false) { $('.alert', text: ~/.+/) }
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    void login(String username, String password) {
        usernameInput = username
        passwordInput = password
        submitButton.click()
    }
}