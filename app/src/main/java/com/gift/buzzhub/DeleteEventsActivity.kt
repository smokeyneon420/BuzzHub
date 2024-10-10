
package com.gift.buzzhub

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DeleteEventsActivity : AppCompatActivity() {

    lateinit var eventList:ArrayList<Events>
    lateinit var deleteEventsActivityRecycler: RecyclerView
    lateinit var adapter: DeleteEventsAdapter
    val eventDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val eventMyReference: DatabaseReference = eventDatabase.reference.child("Events").child("Active Events")
    var hId:String = ""
    var hName:String = ""
    var imgList = ArrayList<Int>()
    var hostEvents = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.deleteEventsMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        deleteEventsActivityRecycler = findViewById(R.id.deleteEventsActivityRecycler)
        eventList = ArrayList<Events>()
        hId = intent.getStringExtra("hostId").toString()
        hostEvents = intent.getIntExtra("hostEvents",0)
        hName = intent.getStringExtra("hostName").toString()
        val hostClicks = intent.getIntExtra("hostClicks",0)
        val hostCategory = intent.getStringExtra("hostCategory")
        retrieveDataFromDatabase(imgList)

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
                deleteEventsActivityRecycler.layoutManager = LinearLayoutManager(this@DeleteEventsActivity)
                fillArray(imgList)
                adapter = DeleteEventsAdapter(eventList,imgList,this@DeleteEventsActivity)
                deleteEventsActivityRecycler.adapter = adapter

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
                        hostActiveMap["hostEvents"] = hostEvents - 1
                        hostActiveReference.child(hId).updateChildren(hostActiveMap)
                        Toast.makeText(this@DeleteEventsActivity,"The event was deleted successfully", Toast.LENGTH_SHORT).show()
                    }
                }).attachToRecyclerView(deleteEventsActivityRecycler)


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
