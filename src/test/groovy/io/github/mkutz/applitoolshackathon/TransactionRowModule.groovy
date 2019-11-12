package io.github.mkutz.applitoolshackathon

import geb.Module

import static java.lang.Integer.parseInt

@SuppressWarnings("GrMethodMayBeStatic")
class TransactionRowModule extends Module {

    static content = {
        amountCell { $('td', 4) }
    }

    Integer getAmountInCents() {
        parseInt(amountCell?.text()?.replaceAll(/[^-0-9]/, ''))
    }
}