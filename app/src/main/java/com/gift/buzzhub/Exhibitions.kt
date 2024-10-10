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

class Exhibitions : AppCompatActivity() {

    lateinit var recyclerView : RecyclerView

    var exhibitionsEventList = ArrayList<String>()
    var detailsEventList = ArrayList<String>()
    var imageList = ArrayList<Int>()

    lateinit var adapter : exhibitionsAdapter


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_exhibitions)
        //status bar change colour to buzzhub Blue (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#009988")
        }


        recyclerView = findViewById(R.id.exhibitionsRecyclerView)

        recyclerView.layoutManager = LinearLayoutManager(this)

        exhibitionsEventList.add("Sibonelo")
        exhibitionsEventList.add("Gift")
        exhibitionsEventList.add("Dlamini")

        detailsEventList.add("I am Kabayi.")
        detailsEventList.add("I am the President.")
        detailsEventList.add("I will put us on the map!")

        imageList.add(R.drawable.comedy)
        imageList.add(R.drawable.exhibitions)
        imageList.add(R.drawable.johannesburg)

        adapter = exhibitionsAdapter(exhibitionsEventList, detailsEventList, imageList, this)

        recyclerView.adapter = adapter

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}