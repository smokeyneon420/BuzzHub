package com.gift.buzzhub


import android.os.Bundle
import android.view.WindowManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gift.buzzhub.databinding.ActivityHostDashboardBinding

class HostDashboard : AppCompatActivity() {

    lateinit var binding: ActivityHostDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        binding = ActivityHostDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hDashboardMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val eventsFragment = FirstFragment()
        val analyticsFragment = SecondFragment()
        val financialsFragment = ThirdFragment()


        val hostId = intent.getStringExtra("hostId")
        val hostEvents = intent.getIntExtra("hostEvents",0)
        val hostName = intent.getStringExtra("hostName")
        val hostClicks = intent.getIntExtra("hostClicks",0)
        val hostCategory = intent.getStringExtra("hostCategory")

        val eventsBundle = Bundle()
        eventsBundle.putString("hostId",hostId)
        eventsBundle.putInt("hostEvents",hostEvents)
        eventsBundle.putString("hostName",hostName)
        eventsBundle.putInt("hostClicks",hostClicks)
        eventsBundle.putString("hostCategory",hostCategory)
        eventsFragment.arguments = eventsBundle

        val analyticsBundle = Bundle()
        analyticsBundle.putString("hostId",hostId)
        analyticsBundle.putInt("hostEvents",hostEvents)
        analyticsBundle.putString("hostName",hostName)
        analyticsBundle.putInt("hostClicks",hostClicks)
        analyticsFragment.arguments = analyticsBundle





        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(eventsFragment,"Events")
        adapter.addFragment(analyticsFragment,"Analytics")
        adapter.addFragment(financialsFragment,"Financials")

        binding.viewPager.adapter = adapter
        binding.tbLayout.setupWithViewPager(binding.viewPager)




    }

}



