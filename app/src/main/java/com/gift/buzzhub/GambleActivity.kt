package com.gift.buzzhub

import android.graphics.Color
import android.os.Build
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
    val siteNames = arrayListOf("HollywoodBet", "Betway", "LottoStar", "SupaBet")

    lateinit var adapter: GambleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_gambling)
        //status bar change colour to buzzhub Blue (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#009988")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.gambling_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        recyclerView = findViewById(R.id.gamblingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this@GambleActivity)
        gambleSites = arrayListOf(
            "https://m.hollywoodbets.net/",
            "https://reg.betway.co.za/Sports?register=1&btag=P72188-PR24678-CM74973-TS1990856&gclid=CjwKCAjwoJa2BhBPEiwA0l0ImJWvRT9sxObDTWCMDqKcXSvffG8Hw9kSYJsptEhCnmCMAe3MCVU_RhoC59IQAvD_BwE",
            "https://lottostar.co.za/",
            "https://www.supabets.co.za/"
        )


        imagesGamble.add(R.drawable.hollywoodbets)
        imagesGamble.add(R.drawable.betway)
        imagesGamble.add(R.drawable.lottostar)
        imagesGamble.add(R.drawable.supabets)

        adapter= GambleAdapter(gambleSites,imagesGamble,siteNames,this@GambleActivity)
        recyclerView.adapter= adapter





    }
}