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

class Comedy() : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    var comedyEventList = ArrayList<String>()
    var detailsEventList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    lateinit var adapter : exhibitionsAdapter

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_comedy)
        //status bar change colour to buzzhub Blue (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#009988")
        }

        recyclerView = findViewById(R.id.comedyRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        comedyEventList.add("Sibonelo")
        comedyEventList.add("Gift")
        comedyEventList.add("Dlamini")

        detailsEventList.add("I am Kabayi.")
        detailsEventList.add("I am the President.")
        detailsEventList.add("I will put us on the map!")

        imageList.add(R.drawable.comedy)
        imageList.add(R.drawable.exhibitions)
        imageList.add(R.drawable.johannesburg)

        adapter = exhibitionsAdapter(comedyEventList, detailsEventList, imageList, this)

        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}