package io.github.mkutz.applitoolshackathon

import geb.spock.GebSpec

class DynamicContentSpec extends GebSpec {

    MainPage mainPage

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    def setup() {
        to(LoginPage, showAd: true).login(someUsername, somePassword)
        mainPage = at(MainPage)
    }

    def "ads should be displayed"() {
        expect:
        mainPage.ad1.displayed
        mainPage.ad2.displayed
    }
}
