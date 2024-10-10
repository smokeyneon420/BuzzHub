package com.gift.buzzhub

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GambleActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    var gambleSites= ArrayList<String>()
    var imagesGamble= ArrayList<Int>()

    lateinit var adapter: GambleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gambling)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gambling_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        recyclerView = findViewById(R.id.gamblingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@GambleActivity)

        gambleSites.add("Visit hollywoodBets.co.za")
        gambleSites.add("Visit betway.co.za")
        gambleSites.add("Visit lottostars.co.za")
        gambleSites.add("Visit supabet.co.za")

        imagesGamble.add(R.drawable.hollywoodbets)
        imagesGamble.add(R.drawable.betway)
        imagesGamble.add(R.drawable.lottostar)
        imagesGamble.add(R.drawable.supabets)

        adapter= GambleAdapter(gambleSites,imagesGamble,this@GambleActivity)
        recyclerView.adapter= adapter





    }
}