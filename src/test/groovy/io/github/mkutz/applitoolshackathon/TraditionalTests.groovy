package io.github.mkutz.applitoolshackathon

import geb.spock.GebSpec
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import spock.lang.Unroll

class TraditionalTests extends GebSpec {

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    String testName

    def setup() {
        testName = "${specificationContext.currentSpec.name}.${specificationContext.currentIteration.name}"

        driver.manage().window().setPosition(new Point(0, 0))
        driver.manage().window().setSize(new Dimension(1920, 1080))
    }

    /*
     * https://applitools.com/hackathon-instructions#login-page
     */

    def "basic UI elements displayed"() {
        given:
        LoginPage loginPage = browser.to(LoginPage)

        expect:
        verifyAll(loginPage) {
            usernameInput.enabled
            passwordInput.enabled
            submitButton.displayed
        }
    }

    /*
     * https://applitools.com/hackathon-instructions#data-driven-test
     */

    @Unroll
    def "login with username \"#username\" & password \"#password\" fails"(
            String username, String password, String expectedErrorMessage) {
        given:
        LoginPage loginPage = browser.to(LoginPage)

        when:
        loginPage.login(username, password)

        then:
        at(LoginPage)

        and:
        loginPage.alert.text() == expectedErrorMessage

        where:
        username     | password     || expectedErrorMessage
        someUsername | ""           || "Password must be present"
        ""           | somePassword || "Username must be present"
        ""           | ""           || "Both Username and Password must be present"
    }

    def "login with username & password succeeds"() {
        given:
        LoginPage loginPage = browser.to(LoginPage)

        when:
        loginPage.login(someUsername, somePassword)

        then:
        at(MainPage)
    }

    /*
     * https://applitools.com/hackathon-instructions#table-sort
     */

    def "sorting by amount"() {
        given:
        to(LoginPage).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)

        when:
        mainPage.transactionsTable.amountHeader.click()

        then:
        mainPage.transactionsTable.transactions.amountInCents ==
                old(mainPage.transactionsTable.transactions.amountInCents.sort())
    }

    /*
     * https://applitools.com/hackathon-instructions#chart-test
     */

    def "chart is displayed"() {
        given:
        to(LoginPage).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)

        expect:
        !mainPage.expensesChart.displayed

        when:
        mainPage.compareExpensesLink.click()

        then:
        mainPage.expensesChart.displayed

        // This does not really check the content of the chart since there is no way to validate a canvas via Selenium
        // only.
    }

    def "add data set for next year"() {
        // Here we need to check a change in the canvas. This is not possible using Selenium only.
    }

    /*
     * https://applitools.com/hackathon-instructions#dynamic-content
     */

    def "ads should be displayed"() {
        given:
        to(LoginPage, showAd: true).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)

        expect:
        mainPage.ad1.displayed
        mainPage.ad2.displayed
    }

}
