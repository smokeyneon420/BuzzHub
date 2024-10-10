package com.gift.buzzhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class concertsFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter: ConcertsPageAdapter
    var namesList = ArrayList<String>()
    var priceList = ArrayList<String>()
    var detailsList = ArrayList<String>()
    var imgList = ArrayList<Int>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_concerts_page, container, false)

        recyclerView = view.findViewById(R.id.concertsRecycleView)
        recyclerView.layoutManager = LinearLayoutManager(context)
        fillArray(namesList,detailsList,imgList)
        adapter = ConcertsPageAdapter(namesList,detailsList,priceList,imgList,requireContext())
        recyclerView.adapter = adapter





        return view
    }
fun fillArray(nameList:ArrayList<String>, detailsList: ArrayList<String>,imgList: ArrayList<Int>) {
    nameList.add("Anthony Hamilton Live @SunArena")
    nameList.add("Taylor Swift Eras Tour 2024")
    nameList.add("Louis the Child LIve @Sun Square Garden")
    nameList.add("Freshly Ground Live")

    detailsList.add("Saturday,May 25 2024, SunArena, Time\nSquare Casino, R479 general. Booking")
    detailsList.add("Sunday,September 22 2024, SunArena, Time\n Square Casino, R1660 general")
    detailsList.add("Saturday,27 April 2024, R250 general\n access. Booking available")
    detailsList.add("Saturday,4 May 2023, Vossloorus Gardens,\nTickets start at R250 General")

    priceList.add("R500")
    priceList.add("R900")
    priceList.add("R200")
    priceList.add("R360")

    imgList.add(R.drawable.colourfest)
    imgList.add(R.drawable.dstvdeliciousimg)
    imgList.add(R.drawable.justdanceimg)
    imgList.add(R.drawable.rockingthedaisiesimg)


}

}