package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class DeleteEventsFragment : DialogFragment() {

    lateinit var eventList:ArrayList<Events>
    lateinit var deleteEventsRecyclerView: RecyclerView
    lateinit var adapter: DeleteEventsAdapter
    lateinit var btnBackDeleteEvents: Button
    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference = eventDatabase.reference.child("Events").child("Active Events")
    val hostDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val hostActiveReference: DatabaseReference = hostDatabase.reference.child("Hosts")
    var hId:String = ""
    var hName:String = ""
    var imgList = ArrayList<Int>()
    var hEvents = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_delete_events, container, false)
        // Inflate the layout for this fragment
        eventList = ArrayList<Events>()

        hName = arguments?.getString("hostName") ?: "Guest"
        hId = arguments?.getString("hostId") ?: "N/A"
        val hClicks = arguments?.getInt("hostClicks") ?: 0
        hEvents = arguments?.getInt("hostEvents") ?: 0
        val hCategory = arguments?.getString("hostCategory") ?: "N/A"

        deleteEventsRecyclerView = view.findViewById(R.id.deleteEventsRecyclerView)
        btnBackDeleteEvents = view.findViewById(R.id.btnBackDeleteEvents)
        btnBackDeleteEvents.setOnClickListener{
            this@DeleteEventsFragment.dismiss()
        }
        retrieveDataFromDatabase(imgList)



        return view
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogTheme)
    }

    fun retrieveDataFromDatabase(imgList: ArrayList<Int>) {
        eventMyReference.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                eventList.clear()
                for(eachEvent in snapshot.children){
                    val event = eachEvent.getValue(Events::class.java)
                    if(event != null && event.eventHostId == hId && event.eventHostName == hName){
                        eventList.add(event)
                    }
                }
                deleteEventsRecyclerView.layoutManager = LinearLayoutManager(context)
                fillArray(imgList)
                adapter = DeleteEventsAdapter(eventList,imgList,requireContext())
                deleteEventsRecyclerView.adapter = adapter

                ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,
                    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                    override fun onMove(
                        recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder
                    ): Boolean {
                        TODO("Not yet implemented")
                    }

                    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                        val id = adapter.getEventId(viewHolder.adapterPosition)

                        eventMyReference.child(id).removeValue()
                        val hostActiveMap = mutableMapOf<String, Any>()
                        hostActiveMap["hostEvents"] = hEvents - 1
                        hostActiveReference.child(hId).updateChildren(hostActiveMap)
                        Toast.makeText(requireContext(),"The event was deleted successfully",Toast.LENGTH_SHORT).show()
                    }
                }).attachToRecyclerView(deleteEventsRecyclerView)


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