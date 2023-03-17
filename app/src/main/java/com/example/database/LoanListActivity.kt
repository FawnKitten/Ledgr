package com.example.database

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import database.R

class LoanListActivity : AppCompatActivity() {
    companion object {
        const val TAG = "LoanListActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_list)
        Log.d(TAG, "onCreate: Hello, world!")
    }
}