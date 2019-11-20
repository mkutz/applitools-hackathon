package io.github.mkutz.applitoolshackathon

import de.retest.recheck.Recheck
import de.retest.recheck.RecheckImpl
import geb.spock.GebSpec
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point
import spock.lang.Unroll

class TraditionalTests extends GebSpec {

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    String testName

    def setup() {
        testName = "${specificationContext.currentSpec.name}.${specificationContext.currentFeature.name}"

        driver.manage().window().setPosition(new Point(0, 0))
        driver.manage().window().setSize(new Dimension(1024, 768))
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
    def "login with username \"#username\" & password \"#password\" fails"(String username, String password) {
        given:
        LoginPage loginPage = browser.to(LoginPage)

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
        Recheck re = new RecheckImpl().tap {
            startTest("${specificationContext.currentSpec.name}.${specificationContext.currentFeature.name}")
        }

        and:
        to(LoginPage).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)

        expect:
        !mainPage.expensesChart.displayed

        when:
        mainPage.compareExpensesLink.click()

        then:
        mainPage.expensesChart.displayed

        and:
        re.check(mainPage.expensesChart.singleElement(), "chart")
        re.capTest()
    }

    def "add data set for next year"() {
        given:
        Recheck re = new RecheckImpl().tap {
            startTest(testName)
        }

        and:
        to(LoginPage).login(someUsername, somePassword)
        MainPage mainPage = at(MainPage)
        mainPage.compareExpensesLink.click()

        when:
        mainPage.addDataForNextYearButton.click()

        and:
        sleep(2000)

        then:
        re.check(mainPage.expensesChart.singleElement(), "chart with next year")
        re.capTest()
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
