package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
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
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.gift.buzzhub.databinding.ActivityHomePageBinding
import com.google.android.material.tabs.TabItem
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class HomePage : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    lateinit var recyclerView: RecyclerView
    lateinit var homePageBinding: ActivityHomePageBinding
    var nameList = ArrayList<String>()
    var detailsList = ArrayList<String>()
    var imageList = ArrayList<Int>()
    lateinit var adapter: ViewPagerAdapter
    lateinit var menuSpinner: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        homePageBinding = ActivityHomePageBinding.inflate(layoutInflater)
        val view = homePageBinding.root
        setContentView(view)
        //status bar change colour to buzzhub Green (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#98FB98")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainHome)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        menuSpinner = findViewById(R.id.menu_spinner)


        // CATAGORIES RECYCLER VIEW CODE!!
        // recyclerView = findViewById(R.id.categoryRecycleView)
        // recyclerView.layoutManager = LinearLayoutManager(this@HomePage)
        // fillArray(nameList,detailsList,imageList)

        // adapter = HomePageAdapter(nameList, detailsList, imageList, this@HomePage, this)
        // recyclerView.adapter = adapter






       // SPINNER
        var menuArray = R.array.Menu

        menuAdapter(this@HomePage,menuArray,menuSpinner)

        // TABS LAYOUT CODE!!

        var tabsArray = arrayOf("FESTIVALS" ,"SPORTING EVENTS","CONCERTS","GAMBLING" )
        var tabLayout = findViewById<TabLayout>(R.id.allTabs)
        var viewPager = findViewById<ViewPager2>(R.id.viewPager)




        var fragments= arrayOf(FestivalsFragment(),Sporting_events_Fragment(),concertsFragment(),GamblingFragment())

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
        imageList.add(R.drawable.gambling)
    }

    fun menuAdapter(context: Context, array:Int, menuSpinner:Spinner){
        val arrayAdapter = ArrayAdapter.createFromResource(context,
            array,
            android.R.layout.simple_spinner_item)

        arrayAdapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        menuSpinner.adapter = arrayAdapter

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if(parent!=null){
            val a = parent.getItemAtPosition(position)
            when(a){
                "Log Out" -> {val intent = Intent(this@HomePage,LoginPage::class.java)
                              startActivity(intent)
                              Toast.makeText(applicationContext,"You have selected${parent.getItemAtPosition(position)}",
                                  Toast.LENGTH_LONG).show()
                                finish()}
                "Profile" -> {val intent = Intent(this@HomePage,ProfilePage::class.java)
                               startActivity(intent)
                               Toast.makeText(applicationContext,"You have selected${parent.getItemAtPosition(position)}",
                                   Toast.LENGTH_LONG).show()}
                "Settings" -> {val intent = Intent(this@HomePage,SettingsPage::class.java)
                               startActivity(intent)
                               Toast.makeText(applicationContext,"You have selected${parent.getItemAtPosition(position)}",
                                   Toast.LENGTH_LONG).show()}
                        //"Recommendations" -> Leads to Recommendations screen
            }
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }
}

