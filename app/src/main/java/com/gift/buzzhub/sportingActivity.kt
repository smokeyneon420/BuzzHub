package com.gift.buzzhub

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SportingEventsActivity() : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var event_name = ArrayList<String>()
    var image_list = ArrayList<Int>()
    lateinit var adapter : events_adapter



    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sporting_events)

        //status bar change colour to buzzhub Blue (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#009988")
        }


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        event_name.add("Alberton FC VS Boksburg Eagles")
        event_name.add("HockeyForChange Tournament")
        event_name.add("CANTSA Breast Cancer Marathon")
        event_name.add("East Rand Athletics Day of Fun")

        image_list.add(R.drawable.soccer)
        image_list.add(R.drawable.hockey)
        image_list.add(R.drawable.marathon)
        image_list.add(R.drawable.athletics)

        adapter = events_adapter(event_name,image_list,this@SportingEventsActivity)

        recyclerView.adapter = adapter
        }
    }
