package com.gift.buzzhub

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

val editDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
val editReference: DatabaseReference = editDatabase.reference.child("Events").child("Active Events")
class EditEventsActivity : AppCompatActivity() {

    lateinit var txtEditEventName:TextInputEditText
    lateinit var txtEditEventDetails:TextInputEditText
    lateinit var textViewCapacity:TextView
    lateinit var textViewPrice:TextView
    lateinit var editEventNamebtn:Button
    lateinit var editEventDetailsbtn:Button
    lateinit var editCapacitySpinner: Spinner
    lateinit var editPriceSpinner:Spinner
    lateinit var editEventCapacitybtn:Button
    lateinit var editEventPricebtn:Button
    var selectedCapacity:Long = 0
    var selectedPrice: Double = 0.0
    var eventId:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_events)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editEventsMain)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtEditEventName = findViewById(R.id.txtEditEventName)
        textViewCapacity = findViewById(R.id.textViewCapacity)
        textViewPrice = findViewById(R.id.textViewPrice)
        txtEditEventDetails = findViewById(R.id.txtEditEventDetails)
        editEventNamebtn = findViewById(R.id.editEventNamebtn)
        editEventDetailsbtn = findViewById(R.id.editEventDetailsbtn)
        editCapacitySpinner = findViewById(R.id.editCapacitySpinner)
        editPriceSpinner = findViewById(R.id.editPriceSpinner)
        editEventCapacitybtn = findViewById(R.id.editEventCapacitybtn)
        editEventPricebtn = findViewById(R.id.editEventPricebtn)

        val capacityNumbers = (1..10000).toList()
        val capacityAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,capacityNumbers)
        capacityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        editCapacitySpinner.adapter = capacityAdapter

        editCapacitySpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if(parent != null){
                    selectedCapacity = parent.getItemAtPosition(position).toString().toLong()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val priceAdapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,capacityNumbers)
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        editPriceSpinner.adapter = priceAdapter

        editPriceSpinner.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?, view: View?, position: Int, id: Long
            ) {
                if(parent != null){
                    selectedPrice = parent.getItemAtPosition(position).toString().toDouble()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }

        val eventName = intent.getStringExtra("eventName")
        txtEditEventName.setText(eventName)
        val eventDetails = intent.getStringExtra("eventDetails")
        txtEditEventDetails.setText(eventDetails)
        val eventPrice = intent.getDoubleExtra("eventPrice",0.0)
        textViewPrice.text = eventPrice.toString()
        val eventCapacity = intent.getStringExtra("eventCapacity")
        textViewCapacity.text = eventCapacity
        eventId = intent.getStringExtra("eventId").toString()

        editEventCapacitybtn.setOnClickListener{
            val currentCapacity = selectedCapacity
            if(currentCapacity.toString() != eventCapacity){
                changeEventDetails(currentCapacity.toString(),eventId)
            }
            else{
                alertCapacityDialog()
            }
        }

        editEventPricebtn.setOnClickListener{
            val currentPrice = selectedPrice
            if(currentPrice != eventPrice){
                changeEventPrice(currentPrice.toString(),eventId)
            }
            else{
                alertPriceDialog()
            }
        }

        editEventNamebtn.setOnClickListener{
            val currentName = txtEditEventDetails.text.toString()
            if(currentName != eventName){
                changeEventName(currentName,eventId)
            }
            else{
                alertNameDialog()
            }

        }

        editEventDetailsbtn.setOnClickListener{
            val currentDetails = txtEditEventDetails.text.toString()
            if(currentDetails != eventDetails){
                changeEventDetails(currentDetails,eventId)
            }
            else{
                alertDetailsDialog()
            }
        }


    }

    fun changeEventName(eventName:String, eventId:String){
        val eventActiveMap = mutableMapOf<String,Any>()
        eventActiveMap["eventName"] = eventName
        editReference.child(eventId).updateChildren(eventActiveMap).addOnCompleteListener {task ->
            if(task.isSuccessful){
                successNameDialog()
            }
            else{
                Toast.makeText(this,"Error Editing Name of Event",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun changeEventDetails(eventDetails:String, eventId:String){
        val eventActiveMap = mutableMapOf<String,Any>()
        eventActiveMap["eventDetails"] = eventDetails
        editReference.child(eventId).updateChildren(eventActiveMap).addOnCompleteListener {task ->
            if(task.isSuccessful){
                successDetailsDialog()
            }
            else{
                Toast.makeText(this,"Error Editing Details of Event",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun changeEventPrice(eventPrice:String, eventId:String){
        val eventActiveMap = mutableMapOf<String,Any>()
        eventActiveMap["eventPrice"] = eventPrice
        editReference.child(eventId).updateChildren(eventActiveMap).addOnCompleteListener {task ->
            if(task.isSuccessful){
                successPriceDialog()
            }
            else{
                Toast.makeText(this,"Error Editing Price of Event",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun changeEventCapacity(eventCapacity:String, eventId:String){
        val eventActiveMap = mutableMapOf<String,Any>()
        eventActiveMap["eventCapacity"] = eventCapacity
        editReference.child(eventId).updateChildren(eventActiveMap).addOnCompleteListener {task ->
            if(task.isSuccessful){
                successCapacityDialog()
            }
            else{
                Toast.makeText(this,"Error Editing Capacity of Event",Toast.LENGTH_LONG).show()

            }
        }
    }

    fun successNameDialog() {
        AlertDialog.Builder(this) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Name Edited Successfully.")
            /*.setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }*/
            .create()
            .show()
    }

    fun successDetailsDialog() {
        AlertDialog.Builder(this) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Details Edited Successfully.")
            /*.setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }*/
            .create()
            .show()
    }

    fun successPriceDialog() {
        AlertDialog.Builder(this) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Price Edited Successfully.")
            /*.setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }*/
            .create()
            .show()
    }

    fun successCapacityDialog() {
        AlertDialog.Builder(this) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Capacity Edited Successfully.")
            /*.setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }*/
            .create()
            .show()
    }



    fun alertNameDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error:Identical Name ")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Kindly insert a newer event details.")
            .create()
            .show()
    }

    fun alertDetailsDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error:Identical Details ")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Kindly insert newer event details.")
            .create()
            .show()
    }

    fun alertPriceDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error:Identical Details ")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Kindly insert newer event price.")
            .create()
            .show()
    }

    fun alertCapacityDialog() {
        AlertDialog.Builder(this)
            .setTitle("Error:Identical Details ")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Kindly insert newer event capacity.")
            .create()
            .show()
    }
}