package com.gift.buzzhub

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class FestivalsFragment:Fragment() {

    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference =
        eventDatabase.reference.child("Events").child("Active Events")

    lateinit var recyclerView: RecyclerView
    var festivalsNames = ArrayList<String>()
    var festivalsDetailsList = ArrayList<String>()
    var imagesFestivals = ArrayList<Int>()
    var imgList = ArrayList<Int>()
    var eventList = ArrayList<Events>()
    lateinit var Tadapter: UserEventOverviewAdapter

    lateinit var adapter: festivalsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_festivals, container, false)

        recyclerView = view.findViewById(R.id.festivalsRecyclerView)
        fillArray(imgList)
        retrieveDataFromDatabase(imgList)

        return view


    }

    fun fillArray(imgList: ArrayList<Int>) {


        imgList.add(R.drawable.colourfest)
        imgList.add(R.drawable.dstvdeliciousimg)
        imgList.add(R.drawable.justdanceimg)
        imgList.add(R.drawable.rockingthedaisiesimg)


    }

    fun retrieveDataFromDatabase(imgList: ArrayList<Int>) {
        eventMyReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for (eachEvent in snapshot.children) {
                    val event = eachEvent.getValue(Events::class.java)
                    if (event != null && event.eventCategory == "Festivals") {
                        eventList.add(event)
                    }
                }
                recyclerView.layoutManager = LinearLayoutManager(context)
                fillArray(imgList)
                Tadapter = UserEventOverviewAdapter(eventList, imgList, requireContext())
                recyclerView.adapter = Tadapter


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}


