package com.gift.buzzhub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gift.buzzhub.R

class SettingsPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var settingsList = ArrayList<String>()
    lateinit var adapter: SettingsAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings_page)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        recyclerView = findViewById(R.id.settings_recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        settingsList.add("Account")
        settingsList.add("Notification")
        settingsList.add("Payment methods")
        settingsList.add("Display")
        settingsList.add("Help centre")
        settingsList.add("Privacy policy")
        settingsList.add("Delete account")

        adapter = SettingsAdapter(settingsList)
        recyclerView.adapter = adapter


    }
}