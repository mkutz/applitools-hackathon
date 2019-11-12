package io.github.mkutz.applitoolshackathon

import geb.Page

class MainPage extends Page {

    static at = { $('.logo') }

    static content = {
        transactionsTable { $('#transactionsTable').module(TransactionsTableModule) }
    }
}
