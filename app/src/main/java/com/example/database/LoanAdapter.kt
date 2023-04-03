package com.example.database

import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.backendless.Backendless
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import database.R

class LoanAdapter(private val dataSet: MutableList<Loan>, private val context: Activity) :
    RecyclerView.Adapter<LoanAdapter.ViewHolder>() {

    companion object {
        const val TAG = "LoanAdapter"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLoanItemBorrower: TextView
        val textViewLoanItemBalanceRemaining: TextView
        val constraintLayoutLoanListLayout: ConstraintLayout

        init {
            textViewLoanItemBorrower = view.findViewById(R.id.textView_loanItem_borrower)
            textViewLoanItemBalanceRemaining = view.findViewById(R.id.textView_loanItem_balanceRemaining)
            constraintLayoutLoanListLayout = view.findViewById((R.id.constraintLayout_loanItem_layout))
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.loan_item, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        val context = viewHolder.constraintLayoutLoanListLayout.context
        viewHolder.textViewLoanItemBorrower.text = dataSet[position].borrower
        viewHolder.textViewLoanItemBalanceRemaining.text = dataSet[position].balanceRemaining().toString()
//        holder.textViewAmountOwed.text = String.format("$%.2f", loan.initialLoanValue-loan.amountRepaid)
        viewHolder.constraintLayoutLoanListLayout.isLongClickable = true
        viewHolder.constraintLayoutLoanListLayout.setOnLongClickListener {
            val popMenu = PopupMenu(context, viewHolder.textViewLoanItemBorrower)
            popMenu.inflate(R.menu.menu_loan_list_context)
            popMenu.setOnMenuItemClickListener {
                when(it.itemId) {
                    R.id.menu_loanList_delete -> {
                        deleteFromBackendless(position)
                        dataSet.removeAt(position)
                        notifyItemRemoved(position)
                        true
                    }
                    else -> true
                }
            }
            popMenu.show()
            true
        }
    }

    private fun deleteFromBackendless(position: Int) {
        Log.d(TAG, "deleteFromBackendless: Deleting ${dataSet[position]}")
        Backendless.Data.of(Loan::class.java).remove(dataSet[position], object : AsyncCallback<Long> {
            override fun handleResponse(response: Long?) {
                Log.d(TAG, "handleResponse: Deleted.")
            }

            override fun handleFault(fault: BackendlessFault?) {
                Log.d(TAG, "handleFault: Couldn't Delete")
                Log.d(TAG, "handleFault: $fault")
            }

        })
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}