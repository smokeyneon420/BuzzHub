package com.gift.buzzhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FestivalsFragment:Fragment() {

    lateinit var recyclerView : RecyclerView
    var festivalsNames= ArrayList<String>()
    var festivalsDetailsList= ArrayList<String>()
    var imagesFestivals= ArrayList<Int>()

    lateinit var adapter: festivalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_festivals, container, false)

        recyclerView= view.findViewById(R.id.festivalsRecyclerView)

        recyclerView.layoutManager =  LinearLayoutManager(context)

        festivalsNames.add("Cotton Fest Johannesburg 2024")
        festivalsNames.add("ColourFest @Joburg Zoo")
        festivalsNames.add("Just Dance! Indie Music Fest")
        festivalsNames.add("Rocking The Daisies")
        festivalsNames.add("DSTV Delicious Festival: Janet Jackson")
        festivalsNames.add("Gauteng Prawn and Wine Fest")

        festivalsDetailsList.add("R350pp, 16 April 2024, 200 Carr Street, Marshalltown, Johannesburg")
        festivalsDetailsList.add("R200pp, 28 September 2024, Emmarentia Dam, Johannesburg")
        festivalsDetailsList.add("R300, 17 December 2024, Plot 246 Helderburg Estate, Benoni")
        festivalsDetailsList.add("R350, 09 October 2024, SuperSport Park, Johannesburg")
        festivalsDetailsList.add("R800, 21-22 September 2024, Kyalami Grand Prix Circuit, Johannesburg")
        festivalsDetailsList.add("R750, 04 August 2024, Kievits Kroon Estate, Pretoria")

        imagesFestivals.add(R.drawable.cottonfestimg)
        imagesFestivals.add(R.drawable.colourfest)
        imagesFestivals.add(R.drawable.justdanceimg)
        imagesFestivals.add(R.drawable.rockingthedaisiesimg)
        imagesFestivals.add(R.drawable.dstvdeliciousimg)
        imagesFestivals.add(R.drawable.prawnandwinefest)

        adapter= festivalsAdapter(festivalsNames,festivalsDetailsList,imagesFestivals, requireContext())

        recyclerView.adapter = adapter








        return view


    }}


