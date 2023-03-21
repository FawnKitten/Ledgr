package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import com.backendless.persistence.DataQueryBuilder
import database.R

class LoanListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "LoanListActivity"
    }

    lateinit var userId: String
    lateinit var loans: List<Loan>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_list)
        userId = intent?.getStringExtra(LoginActivity.EXTRA_USERID).toString()
        Log.d(TAG, "onCreate: $userId")
        loans = retrieveAllData(userId)
    }

    private fun retrieveAllData(userId: String): List<Loan> {
        Log.d(TAG, "retrieveAllData: Retrieving Loans")
        val where = "ownerId = '$userId'"
        val queryBuilder = DataQueryBuilder.create()
        queryBuilder.whereClause = where
        var loans = listOf<Loan>()
        Backendless.Data.of(Loan::class.java).find(
            queryBuilder,
            object : AsyncCallback<List<Loan>> {
                override fun handleResponse(foundLoans: List<Loan>?) {
                    Log.d(TAG, "handleResponse: $foundLoans")
                    if (foundLoans != null) {
                        loans = foundLoans
                    }
                }

                override fun handleFault(fault: BackendlessFault?) {
                    Log.d(TAG, "handleFault: ${fault?.message}")
                }
            })
        return loans
    }
}