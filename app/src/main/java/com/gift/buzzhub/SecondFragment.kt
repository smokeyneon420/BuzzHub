package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class SecondFragment : Fragment() {

    lateinit var hostName:TextView
    lateinit var hostClicks:TextView
    lateinit var hostEvents:TextView
    lateinit var hostList:ArrayList<Host>
    val mydatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference2: DatabaseReference = mydatabase.reference.child("Hosts")
    var hClicks:Int = 0
    var hEvents:Int = 0



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_second, container, false)
        // Inflate the layout for this fragment

        val hName:String = arguments?.getString("hostName")?:"Guest"

        val hId = arguments?.getString("hostId")?: "N/A"
        hostName = view.findViewById(R.id.hostName)
        hostClicks = view.findViewById(R.id.hostClicks)
        hostEvents = view.findViewById(R.id.hostEvents)
        hostList = ArrayList<Host>()
        hostName.text = hName

        retrieveDataFromDatabase(hId)

        return view
    }

    /*fun retrieveDataFromDatabase(hostId: String, onDataRetrieved: (Int, Int) -> Unit) {
        myReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                lateinit var hostList:ArrayList<Host>
                hostList.clear()
                for (eachHost in snapshot.children) {
                    val host = eachHost.getValue(Host::class.java)
                    if (host != null) {
                        hostList.add(host)
                    }
                }

                for (host in hostList) {
                    if (host.hostId == hostId) {
                        onDataRetrieved(host.hostClicks, host.hostEvents)
                        return // Exit the loop once the host is found
                    }
                }

                // If host not found, you might want to call onDataRetrieved with default values
                onDataRetrieved(0, 0)
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error, potentially call onDataRetrieved with error indication
                Log.e("Database Error", "Error retrieving data: ${error.message}")
                onDataRetrieved(-1, -1) // Example: -1 to indicate an error
            }
        })
    }*/

    fun retrieveDataFromDatabase(hostId: String) {
        myReference2.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {hostList.clear()
                for (eachHost in snapshot.children) {
                    val host = eachHost.getValue(Host::class.java)
                    if (host != null) {
                        hostList.add(host)
                    }
                }

                for (host in hostList) {
                    if (host.hostId == hostId) {
                        hClicks = host.hostClicks
                        hEvents = host.hostEvents
                        // Update TextViews here
                        hostClicks.text = hClicks.toString()
                        hostEvents.text = hEvents.toString()
                        break
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("SecondFragment", "Database error: ${error.message}")
            }
        })
    }





}