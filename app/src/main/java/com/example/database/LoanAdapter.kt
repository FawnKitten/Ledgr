package com.example.database

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import database.R

class LoanAdapter(private val dataSet: MutableList<Loan>) :
    RecyclerView.Adapter<LoanAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textViewLoanItemLendee: TextView
        val textViewLoanItemBalanceRemaining: TextView
        val constraintLayoutLoanListLayout: ConstraintLayout

        init {
            textViewLoanItemLendee = view.findViewById(R.id.textView_loanItem_lendee)
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
        viewHolder.textViewLoanItemLendee.text = dataSet[position].lendee
        viewHolder.textViewLoanItemBalanceRemaining.text = dataSet[position].ballanceRemaining().toString()
//        viewHolder.constraintLayoutLoanListLayout.isLongClickable = true
//        viewHolder.constraintLayoutLoanListLayout.setOnLongClickListener {
//            val popupMenu = PopupMenu(context, viewHolder.textViewLoanItemLendee)
//            popupMenu.inflate(R.menu)
//            true
//        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size
}