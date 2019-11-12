package io.github.mkutz.applitoolshackathon

import geb.spock.GebSpec
import spock.lang.Subject

class TableSortSpec extends GebSpec {

    @Subject
    MainPage mainPage

    static final String someUsername = "username"
    static final String somePassword = "p4ssw0rd"

    def setup() {
        to(LoginPage).login(someUsername, somePassword)
        mainPage = at(MainPage)
    }

    def "sorting by amount"() {
        when:
        mainPage.transactionsTable.amountHeader.click()

        then:
        mainPage.transactionsTable.transactions.amountInCents ==
                old(mainPage.transactionsTable.transactions.amountInCents.sort())
    }
}
