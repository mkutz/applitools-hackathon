package io.github.mkutz.applitoolshackathon

import geb.Page

class MainPage extends Page {

    static at = { $('.logo') }

    static content = {
        transactionsTable { $('#transactionsTable').module(TransactionsTableModule) }
        compareExpensesLink { $('#showExpensesChart') }
        expensesChart(required: false) { $('#canvas') }
        addDataForNextYearButton(required: false) { $('#addDataset') }
        ad1(required: false) { $('#flashSale img') }
        ad2(required: false) { $('#flashSale2 img') }
    }
}
