package com.gift.buzzhub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AccountPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var accountList = ArrayList<String>()
    lateinit var adapter: AccountAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_account)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        recyclerView = findViewById(R.id.account_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        accountList.add("Update name")
        accountList.add("Update email")
        accountList.add("Update profile")
        accountList.add("Two factor authentication")

        adapter = AccountAdapter(this, accountList)
        recyclerView.adapter = adapter


    }
}