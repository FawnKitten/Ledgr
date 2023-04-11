package com.example.database

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.*


/**
 * Loan class represents a loan from `borrower` to the user
 * @param borrower The person that got lent money
 * @param description Why the person had to be lent money
 * @param initialLoan The initial monetary value of the loan in cents
 * @param dateLoaned The day the initial loan was made
 * @param amountRepaid Amount repaid thus far
 * @param dateFullRepayment Date when the full amount was repaid, null if it was not fully repaid
 * @param isFullyRepaid Whether the due was paid
 * @param ownerId the objectId of the user responsible for the loan
 * @param objectId database Id for the object
 */
@Parcelize
data class Loan(
    var borrower: String = "NO USER",
    var description: String = "REQUESTED MONEY FOR PURCHASE",
    var initialLoan: Int = 0, // 0 Cents
    var dateLoaned: Date = Date(0),
    var amountRepaid: Int = 0, // Also cents
    var dateFullRepayment: Date? = null,
    var isFullyRepaid: Boolean = false,
    var ownerId: String? = null,
    var objectId: String? = null,
) : Parcelable {
    fun balanceRemaining(): Int {
        return initialLoan - amountRepaid
    }
}
