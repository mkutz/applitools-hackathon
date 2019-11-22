package io.github.mkutz.applitoolshackathon

import geb.Page
import geb.module.PasswordInput
import geb.module.TextInput

class LoginPage extends Page {

    static at = { $('h4', text: "Login Form") }

    static content = {
        usernameInput { $('#username').module(TextInput) }
        passwordInput { $('#password').module(PasswordInput) }
        submitButton { $('#log-in') }
        alert(required: false) { $('.alert', text: ~/.+/) }

        usernameLabel { $('label', text: 'Username') }
        passwordLabel { $('label', text: 'Password') }
        twitterIcon { $('img', src: 'img/social-icons/twitter.png') }
        facebookIcon { $('img', src: 'img/social-icons/facebook.png') }
        linkedInIcon { $('img', src: 'img/social-icons/linkedin.png') }
    }

    @SuppressWarnings("GrMethodMayBeStatic")
    void login(String username, String password) {
        usernameInput = username
        passwordInput = password
        submitButton.click()
    }
}