package io.github.mkutz.applitoolshackathon

import geb.spock.GebSpec

abstract class LoggedInSpec extends GebSpec {

    MainPage mainPage

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    def setup() {
        to(LoginPage).login(someUsername, somePassword)
        mainPage = at(MainPage)
    }
}
