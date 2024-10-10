package com.gift.buzzhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class Exhibitions : Fragment() {


    lateinit var recyclerView: RecyclerView
    var event_name = ArrayList<String>()
    var event_details = ArrayList<String>()
    var event_price = ArrayList<String>()
    var image_list = ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_exhibitions, container, false)
        recyclerView = view.findViewById(R.id.recyclerViewExhibitions)
        recyclerView.layoutManager = LinearLayoutManager(context)

        event_name.add("Alberton FC VS Boksburg Eagles")
        event_name.add("CANTSA Breast Cancer Marathon")
        event_name.add("HockeyForChange Tournament")
        event_name.add("CANTSA Breast Cancer Marathon")
        event_name.add("East Rand Athletics Day of Fun")
        event_name.add("CANTSA Breast Cancer Marathon")

        event_details.add("874 Katlehong Avenue, Johannesburg")
        event_details.add("474 Brian Avenue, Pretoria")
        event_details.add("45 Groove Street, Kempton Park")
        event_details.add("841 Katlehong Avenue, Eden Park")

        event_price.add("R900")
        event_price.add("R650")
        event_price.add("R210")
        event_price.add("R580")

        image_list.add(R.drawable.soccer)
        image_list.add(R.drawable.hockey)


        // FRAGMENT STILL NEEDS AN ADAPTER CLASS


        return view
    }
}
