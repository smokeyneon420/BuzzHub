package com.gift.buzzhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class GamblingFragment : Fragment() {


    lateinit var recyclerView: RecyclerView
    var gambleSites= ArrayList<String>()
    var gambleSitesNames= ArrayList<String>()
    var imagesGamble= ArrayList<Int>()

    lateinit var adapter: GambleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.activity_gambling, container, false)


        recyclerView = view.findViewById(R.id.gamblingRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context)

        gambleSites.add("Visit hollywoodBets.co.za")
        gambleSites.add("Visit betway.co.za")
        gambleSites.add("Visit lottostars.co.za")
        gambleSites.add("Visit supabet.co.za")

        gambleSitesNames.add("HollywoodBets")
        gambleSitesNames.add("Betway")
        gambleSitesNames.add("Lottostars")
        gambleSitesNames.add("Supabet")

        imagesGamble.add(R.drawable.hollywoodbets)
        imagesGamble.add(R.drawable.betway)
        imagesGamble.add(R.drawable.lottostar)
        imagesGamble.add(R.drawable.supabets)

        adapter= GambleAdapter(gambleSites,imagesGamble,gambleSitesNames,requireContext())
        recyclerView.adapter= adapter

        return view
    }

            }

