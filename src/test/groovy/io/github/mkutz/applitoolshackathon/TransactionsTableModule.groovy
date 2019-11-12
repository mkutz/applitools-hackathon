package io.github.mkutz.applitoolshackathon

import geb.Module

class TransactionsTableModule extends Module {

    static content = {
        amountHeader { $('#amount') }
        transactions { $('tbody tr').moduleList(TransactionRowModule) }
    }
}
