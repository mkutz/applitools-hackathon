package io.github.mkutz.applitoolshackathon

import geb.Page

class MainPage extends Page {

    static at = { $('.logo') }

    static content = {
        transactionsTable { $('#transactionsTable').module(TransactionsTableModule) }
        compareExpensesLink { $('#showExpensesChart') }
        expensesChart(required: false, wait: true) { $('#canvas') }
        addDataForNextYearButton(required: false, wait: true) { $('#addDataset') }
        ad1(required: false) { $('#flashSale img') }
        ad2(required: false) { $('#flashSale2 img') }
    }
}
