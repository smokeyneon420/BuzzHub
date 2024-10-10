package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gift.buzzhub.databinding.ActivityHomePageBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomePage : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var recyclerView: RecyclerView
    lateinit var homePageBinding: ActivityHomePageBinding
    var nameList = ArrayList<String>()
    var detailsList = ArrayList<String>()
    var imageList = ArrayList<Int>()
    lateinit var adapter: HomePageAdapter
    lateinit var menuSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homePageBinding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = homePageBinding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainHome)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        menuSpinner = findViewById(R.id.menu_spinner)

        var menuArray = R.array.Menu

        menuAdapter(this@HomePage,menuArray,menuSpinner)




        /// TAB LAYOUT

        val tabsArray = arrayOf("FESTIVALS" ,"SPORTING EVENTS" ,"GAMBLING","EXHIBITIONS")

        // MISSING FRAGMENTS ::  CONCERTS , COMEDY SHOW
        // INCOMPLETE :: EXHIBITIONS


        val tabLayout = findViewById<TabLayout>(R.id.allTabs)
        val viewPager = findViewById<ViewPager2>(R.id.viewPager)

        val fragments = arrayOf(FestivalsFragment(),Sporting_events_Fragment(),GamblingFragment(),Exhibitions())

        val adapter = ViewPagerAdapter(fragments,supportFragmentManager, lifecycle)

        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout,viewPager){tab,position ->
            tab.text = "${tabsArray[position]}"}.attach()


    }

    fun fillArray(nameList:ArrayList<String>, detailsList: ArrayList<String>, imageList:ArrayList<Int>) {
        nameList.add("Concerts")
        nameList.add("Comedy shows")
        nameList.add("Exhibitions")
        nameList.add("Sporting Events")
        nameList.add("Festivals")
        nameList.add("Gambling")

        detailsList.add("See what concerts are on!")
        detailsList.add("See who's cracking jokes next!")
        detailsList.add("Exhibitions")
        detailsList.add("Sporting Events")
        detailsList.add("See where everyone is jamming")
        detailsList.add("Wanna Take a Risk?")


        imageList.add(R.drawable.lady)
        imageList.add(R.drawable.comedy)
        imageList.add(R.drawable.exhibition)
        imageList.add(R.drawable.ball)
        imageList.add(R.drawable.balloon)
    }

    fun menuAdapter(context: Context, array:Int, menuSpinner:Spinner){
        var arrayAdapter = ArrayAdapter.createFromResource(context,
            array,
            android.R.layout.simple_spinner_item)

        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        menuSpinner.adapter = arrayAdapter
        menuSpinner.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent!=null){
            var a = parent.getItemAtPosition(position)
            when(a){
                "Log Out" -> {var intent = Intent(this@HomePage,LoginPage::class.java)
                              startActivity(intent)
                              Toast.makeText(applicationContext,"You have selected${parent.getItemAtPosition(position)}",
                                  Toast.LENGTH_LONG).show()
                                finish()}
                //"Profile" ->Leads to profile screen
                    //"Settings" ->Leads to Settings screen
                        //"Recommendations" -> Leads to Recommendations screen
            }
            Toast.makeText(this,
                "You have selected ${parent.getItemAtPosition(position)}",
                Toast.LENGTH_LONG).show()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}

