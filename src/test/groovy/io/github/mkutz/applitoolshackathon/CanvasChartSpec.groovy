package io.github.mkutz.applitoolshackathon

import spock.lang.Ignore
import spock.lang.Stepwise

@Stepwise
class CanvasChartSpec extends LoggedInSpec {

    def "chart is displayed"() {
        expect:
        !mainPage.expensesChart.displayed

        when:
        mainPage.compareExpensesLink.click()

        then:
        mainPage.expensesChart.displayed
    }

    @Ignore("hard to automate with Selenium, since a canvas contains no DOM elements")
    def "add data set for next year"() {
        when:
        mainPage.addDataForNextYearButton.click()

        then:
        false
    }
}
