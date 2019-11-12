package io.github.mkutz.applitoolshackathon

class TableSortSpec extends LoggedInSpec {

    def "sorting by amount"() {
        when:
        mainPage.transactionsTable.amountHeader.click()

        then:
        mainPage.transactionsTable.transactions.amountInCents ==
                old(mainPage.transactionsTable.transactions.amountInCents.sort())
    }
}
