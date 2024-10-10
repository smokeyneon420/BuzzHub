package com.gift.buzzhub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SportingEventsActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var event_name = ArrayList<String>()
    var image_list = ArrayList<Int>()
    lateinit var adapter : events_adapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_sporting_events)


        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@SportingEventsActivity)

        event_name.add("Alberton FC VS Boksburg Eagles")
        event_name.add("HockeyForChange Tournament")
        event_name.add("CANTSA Breast Cancer Marathon")
        event_name.add("East Rand Athletics Day of Fun")

        image_list.add(R.drawable.soccer)
        image_list.add(R.drawable.hockey)
        image_list.add(R.drawable.marathon)
        image_list.add(R.drawable.athletics)


        recyclerView.adapter = adapter
        }
    }
