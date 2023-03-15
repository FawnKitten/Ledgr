package com.example.database

import java.util.*

/**
 * Loan class represents a loan from `lendee` to the user
 * @param lendee The person that got lent money
 * @param description Why the person had to be lent money
 * @param initialLoan The initial monetary value of the loan in cents
 * @param dateLent The day the initial loan was made
 * @param amountRepaid Amount repaid thus far
 * @param dateFullRepayment Date when the full amount was repaid, null if it was not fully repaid
 * @param isFullyRepaid Whether the due was paid
 */
data class Loan(
    var lendee: String = "NO USER",
    var description: String = "REQUESTED MONEY FOR PURCHASE",
    var initialLoan: Int = 0, // 0 Cents
    var dateLent: Date = Date(0),
    var amountRepaid: Int = 0, // Also cents
    var dateFullRepayment: Date? = null,
    var isFullyRepaid: Boolean = false
) {
    fun ballanceRemaining(): Int {
        return initialLoan - amountRepaid
    }
}
