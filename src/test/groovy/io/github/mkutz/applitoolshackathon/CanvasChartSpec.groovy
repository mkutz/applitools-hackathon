package io.github.mkutz.applitoolshackathon

import de.retest.recheck.Recheck
import de.retest.recheck.RecheckImpl

class CanvasChartSpec extends LoggedInSpec {

    Recheck re = new RecheckImpl().tap {
        startTest(this.class.simpleName)
    }

    def setup() {
        re.startTest("${specificationContext.currentSpec.name}.${specificationContext.currentFeature.name}")
    }

    def cleanup() {
    }

    def "chart is displayed"() {
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
        mainPage.compareExpensesLink.click()

        when:
        mainPage.addDataForNextYearButton.click()

        and:
        sleep(2000)

        then:
        re.check(mainPage.expensesChart.singleElement(), "chart with next year")
        re.capTest()
    }
}
