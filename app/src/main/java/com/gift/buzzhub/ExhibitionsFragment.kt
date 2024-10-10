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

class ExhibitionsFragment : Fragment(){


    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference = eventDatabase.reference.child("Events").child("Active Events")

    lateinit var recyclerView: RecyclerView
    var imgList = ArrayList<Int>()
    lateinit var adapter : exhibitionsAdapter
    var eventList = ArrayList<Events>()
    lateinit var Tadapter: UserEventOverviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_exhibitions, container, false)

        recyclerView = view.findViewById(R.id.exhibitionsRecyclerView)
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
        eventMyReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for(eachEvent in snapshot.children){
                    val event = eachEvent.getValue(Events::class.java)
                    if(event != null && event.eventCategory == "Exhibitions"){
                        eventList.add(event)
                    }
                }
                recyclerView.layoutManager = LinearLayoutManager(context)
                fillArray(imgList)
                Tadapter = UserEventOverviewAdapter(eventList,imgList,requireContext())
                recyclerView.adapter = Tadapter


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })


    }
}