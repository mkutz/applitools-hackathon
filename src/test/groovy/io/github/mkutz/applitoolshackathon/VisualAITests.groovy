package io.github.mkutz.applitoolshackathon

import com.applitools.eyes.selenium.Eyes
import geb.spock.GebSpec
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import spock.lang.Unroll

import static com.applitools.eyes.MatchLevel.LAYOUT

class VisualAITests extends GebSpec {

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    static final Eyes eyes = new Eyes().tap {
        apiKey = System.getProperty("applitools.apikey")
    }

    String testName

    def setup() {
        testName = "${specificationContext.currentSpec.name}.${specificationContext.currentIteration.name}"

        driver.manage().window().setPosition(new Point(0, 0))
        driver.manage().window().setSize(new Dimension(1920, 1080))

        eyes.open(driver, "Hackathon Demo", testName)
    }

    def cleanup() {
        eyes.closeAsync()
    }

    def cleanupSpec() {
        eyes.abortIfNotClosed()
    }

    /*
     * https://applitools.com/hackathon-instructions#login-page
     */

    def "basic UI elements displayed"() {
        given:
        to(LoginPage)

        expect:
        eyes.checkWindow("login form")

        and:
        eyes.close()
    }

    /*
     * https://applitools.com/hackathon-instructions#data-driven-test
     */

    @Unroll
    def "login with username \"#username\" & password \"#password\" fails"(String username, String password) {
        given:
        LoginPage loginPage = to(LoginPage)

        when:
        loginPage.login(username, password)

        then:
        eyes.checkElement(loginPage.alert.singleElement())
        eyes.close()

        where:
        username     | password
        someUsername | ""
        ""           | somePassword
        ""           | ""
    }

    def "login with username & password succeeds"() {
        given:
        LoginPage loginPage = to(LoginPage)

        when:
        loginPage.login(someUsername, somePassword)

        then:
        at(MainPage)

        and:
        eyes.checkWindow()
        eyes.close()
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
        waitFor {
            mainPage.transactionsTable.transactions.amountInCents ==
                    old(mainPage.transactionsTable.transactions.amountInCents.sort())
        }

        and:
        eyes.checkWindow()
        eyes.close()
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

        and:
        eyes.checkWindow()
        eyes.close()
    }

    def "add data set for next year"() {
        given:
        to(LoginPage).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)
        mainPage.compareExpensesLink.click()

        when:
        mainPage.addDataForNextYearButton.click()

        then:
        eyes.checkWindow()
        eyes.close()
    }

    /*
     * https://applitools.com/hackathon-instructions#dynamic-content
     */

    def "ads should be displayed"() {
        given:
        to(LoginPage, showAd: true).login(someUsername, somePassword)
        at(MainPage)
        eyes.matchLevel = LAYOUT

        expect:
        eyes.checkWindow()
        eyes.close()
    }

}