package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AccountAdapter(var context: Context, var accountList: ArrayList<String>): RecyclerView.Adapter<AccountAdapter.AccountViewHolder>() {

    class AccountViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var textAccounts : TextView = itemView.findViewById(R.id.accountText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.account_design, parent, false)
        return AccountViewHolder(view)

    }

    override fun getItemCount(): Int {
        return accountList.size
    }

    override fun onBindViewHolder(holder: AccountViewHolder, position: Int) {
        holder.textAccounts.text = accountList[position]

        holder.itemView.setOnClickListener {
            val intent = when (position) {
                0 -> Intent(context, UpdateNamePage::class.java)
                1 -> Intent(context, UpdateEmailPage::class.java)
                2 -> Intent(context, ProfilePage::class.java)
                3 -> Intent(context, TwoFactorAuthPage::class.java)
                else -> null
            }
            intent?.let { context.startActivity(it) }

        }

    }


}