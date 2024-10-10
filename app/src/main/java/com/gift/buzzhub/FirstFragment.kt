package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class FirstFragment : Fragment() {

    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference = eventDatabase.reference.child("Events").child("Active Events")
    lateinit var eventOverViewRecyclerView: RecyclerView
    lateinit var addButton: FloatingActionButton
    lateinit var deleteButton: FloatingActionButton
    lateinit var adapter: EventsOverviewAdapter
    lateinit var eventList:ArrayList<Events>
    lateinit var hostList:ArrayList<Host>
    var hId:String = ""
    var hName:String = ""
    var imgList = ArrayList<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view =  inflater.inflate(R.layout.fragment_first, container, false)

        eventOverViewRecyclerView = view.findViewById(R.id.eventOverviewRecyclerView)
        addButton = view.findViewById(R.id.addButton)
        deleteButton = view.findViewById(R.id.deleteButton)
        // Inflate the layout for this fragment
        eventList = ArrayList<Events>()
        hostList = ArrayList<Host>()




        hName = arguments?.getString("hostName") ?: "Guest"
        hId = arguments?.getString("hostId") ?: "N/A"
        val hClicks = arguments?.getInt("hostClicks") ?:0
        val hEvents = arguments?.getInt("hostEvents") ?:0
        val hCategory = arguments?.getString("hostCategory") ?: "N/A"

        retrieveDataFromDatabase(imgList)


        addButton.setOnClickListener {

            val addEventsIntent = Intent(requireContext(), AddEventsActivity::class.java)
            addEventsIntent.putExtra("hostId", hId)
            addEventsIntent.putExtra("hostEvents", hEvents)
            addEventsIntent.putExtra("hostName", hName)
            addEventsIntent.putExtra("hostClicks", hClicks)
            addEventsIntent.putExtra("hostCategory", hCategory)
            try {
                startActivity(addEventsIntent)
            } catch (e: Exception) {
                Log.e("FirstFragment", "Error starting AddEventsActivity", e)
            }


        }

        deleteButton.setOnClickListener{

            val deleteEventsIntent = Intent(requireContext(), DeleteEventsActivity::class.java)
            deleteEventsIntent.putExtra("hostId", hId)
            deleteEventsIntent.putExtra("hostEvents", hEvents)
            deleteEventsIntent.putExtra("hostName", hName)
            deleteEventsIntent.putExtra("hostClicks", hClicks)
            deleteEventsIntent.putExtra("hostCategory", hCategory)
            startActivity(deleteEventsIntent)
        }

        return view


    }

    fun retrieveDataFromDatabase(imgList: ArrayList<Int>) {
        eventMyReference.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for(eachEvent in snapshot.children){
                    val event = eachEvent.getValue(Events::class.java)
                    if(event != null && event.eventHostId == hId && event.eventHostName == hName){
                        eventList.add(event)
                    }
                }
                eventOverViewRecyclerView.layoutManager = LinearLayoutManager(context)
                fillArray(imgList)
                adapter = EventsOverviewAdapter(eventList,imgList,requireContext())
                eventOverViewRecyclerView.adapter = adapter


            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    fun fillArray(imgList: ArrayList<Int>) {


        imgList.add(R.drawable.colourfest)
        imgList.add(R.drawable.dstvdeliciousimg)
        imgList.add(R.drawable.justdanceimg)
        imgList.add(R.drawable.rockingthedaisiesimg)


    }


}