package io.github.mkutz.applitoolshackathon

import geb.spock.GebSpec
import spock.lang.Subject
import spock.lang.Unroll

class LoginSpec extends GebSpec {

    @Subject
    LoginPage loginPage = browser.to(LoginPage)

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    def "basic UI elements displayed"() {
        expect:
        verifyAll(loginPage) {
            usernameInput.enabled
            passwordInput.enabled
            submitButton.displayed
        }
    }

    @Unroll
    def "login with username \"#username\" & password \"#password\" fails"(String username, String password) {
        when:
        loginPage.login(username, password)

        then:
        at(LoginPage).alerts

        where:
        username     | password
        someUsername | ""
        ""           | somePassword
        ""           | ""
    }

    def "login with username & password succeeds"() {
        when:
        loginPage.login(someUsername, somePassword)

        then:
        at(MainPage)
    }
}