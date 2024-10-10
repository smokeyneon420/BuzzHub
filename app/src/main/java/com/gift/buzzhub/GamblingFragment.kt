package com.gift.buzzhub

import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
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

        gambleSites = arrayListOf(
            "https://m.hollywoodbets.net/",
            "https://reg.betway.co.za/Sports?register=1&btag=P72188-PR24678-CM74973-TS1990856&gclid=CjwKCAjwoJa2BhBPEiwA0l0ImJWvRT9sxObDTWCMDqKcXSvffG8Hw9kSYJsptEhCnmCMAe3MCVU_RhoC59IQAvD_BwE",
            "https://lottostar.co.za/",
            "https://www.supabets.co.za/"
        )

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


        disclaimerPopUp()


        return view
    }


    fun disclaimerPopUp(){

        var alertDialog1 = AlertDialog.Builder(requireContext())



        alertDialog1.setTitle("Disclaimer").setIcon(
            R.drawable.error_icon
        ).setCancelable(false)
            .setMessage(getString(R.string.gambling_disclaimer))
            .setPositiveButton("I agree", DialogInterface.OnClickListener { dialog, which ->

                dialog.cancel()

            })
            .setNegativeButton("I do not agree", DialogInterface.OnClickListener { dialog, which ->



            })



            alertDialog1.create().show()





    }


            }

