package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

val database: FirebaseDatabase = FirebaseDatabase.getInstance()
val myReference: DatabaseReference = database.reference.child("Events").child("Active Events")
val hostActiveReference: DatabaseReference = database.reference.child("Hosts")

class AddEventsActivity : AppCompatActivity() {

    lateinit var hName:String
    lateinit var hId:String
    var hEvents:Int = 0
    lateinit var hCategory:String
    lateinit var eventImageButton2: Button
    lateinit var addEventButton2: Button
    lateinit var txtAddEventName: TextInputEditText
    lateinit var txtAddEventDetails: TextInputEditText
    lateinit var eventCapacity2: Spinner
    lateinit var eventPrice2: Spinner

    private var selectedCapacity: Long = 0
    private var selectedPrice: Double = 0.0
    private var eventName = ""
    private var eventDetails = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.addEventsMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        eventImageButton2 = findViewById(R.id.chooseEventImageButton2)
        addEventButton2 = findViewById(R.id.addEventButton2)
        txtAddEventName = findViewById(R.id.txtAddEventName)
        txtAddEventDetails = findViewById(R.id.txtAddEventDetails)
        eventCapacity2 = findViewById(R.id.capacitySpinner2)
        eventPrice2 = findViewById(R.id.priceSpinner2)

        hId = intent.getStringExtra("hostId").toString()
        hEvents = intent.getIntExtra("hostEvents",0)
        hName = intent.getStringExtra("hostName").toString()
        val hostClicks = intent.getIntExtra("hostClicks",0)
        hCategory = intent.getStringExtra("hostCategory").toString()

        val capacityNumbers = (1..10000).toList()
        val capacityAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,capacityNumbers)
        capacityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eventCapacity2.adapter = capacityAdapter

        eventCapacity2.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent != null){
                    selectedCapacity = parent.getItemAtPosition(position).toString().toLong()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }

        val priceAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,capacityNumbers)
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item)
        eventPrice2.adapter = priceAdapter

        eventPrice2.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if (parent != null){
                    selectedPrice = parent.getItemAtPosition(position).toString().toDouble()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        addEventButton2.setOnClickListener{
            eventName = txtAddEventName.text.toString()
            eventDetails = txtAddEventDetails.text.toString()
            val check = checkFields(eventName,eventDetails,selectedCapacity,selectedPrice)
            if(check){
                confirmDialog(eventName,hName,hId,hCategory,eventDetails,selectedCapacity,selectedPrice,hEvents)
            } else{
                alertDialog()
            }
        }




    }

    fun checkFields(txtEventName: String, txtEventDetails: String, eventCapacity: Long, eventPrice: Double): Boolean {
        return !(txtEventName.isBlank() || txtEventDetails.isBlank() || eventCapacity == 0L || eventPrice == 0.0)
    }

    fun alertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error: Empty Field(s)")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Please fill in all fields.")
            .create()
            .show()
    }

    fun successDialog() {
        AlertDialog.Builder(this) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Added Successfully.")
            /*.setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }*/
            .create()
            .show()
    }

    fun confirmDialog(eventName: String, hostName: String, hostId: String, hostCategory: String, eventDetails: String, selectedCapacity: Long, selectedPrice: Double, hostEvents: Int) {
        AlertDialog.Builder(this@AddEventsActivity)
            .setTitle("Confirm Event Addition")
            .setIcon(R.drawable.check_icon)
            .setCancelable(false)
            .setMessage("Are you sure you would like to add the event you specified?")
            .setPositiveButton("Yes") { dialog, _ ->
                addEventToDatabase(eventName, hostName, hostId, hostCategory, eventDetails, selectedCapacity, selectedPrice, hostEvents)
                dialog.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    fun addEventToDatabase(eventName: String, hostName: String, hostId: String, hostCategory: String, eventDetails: String, selectedCapacity: Long, selectedPrice: Double, hostEvents: Int) {
        val id: String = myReference.push().key.toString()
        val events = Events(id, eventName, eventDetails, hostName, hostId, selectedPrice, 0, 0, hostCategory, selectedCapacity, 0)
        myReference.child(id).setValue(events).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                successDialog()
            } else {
                Toast.makeText(this@AddEventsActivity, "Adding of Event Unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
        val hostActiveMap = mutableMapOf<String, Any>()
        hostActiveMap["hostEvents"] = hostEvents + 1
        hostActiveReference.child(hostId).updateChildren(hostActiveMap)
    }
}