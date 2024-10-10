package com.gift.buzzhub

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.auth.User


class Sporting_events_Fragment : Fragment() {

    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference = eventDatabase.reference.child("Events").child("Active Events")


    lateinit var recyclerView: RecyclerView
    var imgList = ArrayList<Int>()
    var eventList = ArrayList<Events>()
    var hId:String = ""
    var hName:String = ""
    lateinit var adapter : events_adapter
    lateinit var Tadapter: UserEventOverviewAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.activity_sporting_events, container, false)
        recyclerView = view.findViewById(R.id.account_recyclerView)
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
                    if(event != null && event.eventCategory == "Sporting Events"){
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
