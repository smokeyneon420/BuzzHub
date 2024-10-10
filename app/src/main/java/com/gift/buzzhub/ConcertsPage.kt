package com.gift.buzzhub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ConcertsPage : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ConcertsPageAdapter
    var namesList = ArrayList<String>()
    var detailsList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_concerts_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        recyclerView = findViewById(R.id.concertsRecycleView)
       // recyclerView.layoutManager = LinearLayoutManager(this@ConcertsPage)
        fillArray(namesList,detailsList)
       // adapter = ConcertsPageAdapter(namesList,detailsList,this@ConcertsPage)
      //  recyclerView.adapter = adapter

    }

    fun fillArray(nameList:ArrayList<String>, detailsList: ArrayList<String>) {
        nameList.add("Anthony Hamilton Live @SunArena")
        nameList.add("Taylor Swift Eras Tour 2024")
        nameList.add("Louis the Child LIve @Sun Square Garden")
        nameList.add("Freshly Ground Live")

        detailsList.add("Saturday,May 25 2024, SunArena, Time\nSquare Casino, R479 general. Booking")
        detailsList.add("Sunday,September 22 2024, SunArena, Time\n Square Casino, R1660 general")
        detailsList.add("Saturday,27 April 2024, R250 general\n access. Booking available")
        detailsList.add("Saturday,4 May 2023, Vossloorus Gardens,\nTickets start at R250 General")



    }
}