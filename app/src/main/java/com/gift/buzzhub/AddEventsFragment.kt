
package com.gift.buzzhub

/*
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

*/
/*val database: FirebaseDatabase = FirebaseDatabase.getInstance()
val myReference: DatabaseReference = database.reference.child("Events").child("Active Events")
val hostActiveReference: DatabaseReference = database.reference.child("Hosts")*//*


class AddEventsFragment : DialogFragment() {
    lateinit var eventImageButton: Button
    lateinit var addEventButton: Button
    lateinit var txtEventName: TextInputEditText
    lateinit var txtEventDetails: TextInputEditText
    lateinit var eventCapacity: Spinner
    lateinit var eventPrice: Spinner
    lateinit var backButtonAddEvents: Button

    private var selectedCapacity: Long = 0
    private var selectedPrice: Double = 0.0
    private var eventName = ""
    private var eventDetails = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_events, container, false)
        eventImageButton = view.findViewById(R.id.chooseEventImageButton)
        addEventButton = view.findViewById(R.id.addEventButton)
        txtEventName = view.findViewById(R.id.txtEventName)
        txtEventDetails = view.findViewById(R.id.txtEventDetails)
        eventCapacity = view.findViewById(R.id.capacitySpinner)
        eventPrice = view.findViewById(R.id.priceSpinner)
        backButtonAddEvents = view.findViewById(R.id.backButtonAddEvents)

        val hName: String= arguments?.getString("hostName") ?: "Guest"
        val hId: String = arguments?.getString("hostId") ?: "N/A"
        val hClicks = arguments?.getInt("hostClicks") ?: 0
        val hEvents = arguments?.getInt("hostEvents") ?: 0
        val hCategory = arguments?.getString("hostCategory") ?: "N/A"


        val capacityNumbers = (1..10000).toList()
        val capacityAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, capacityNumbers)
        capacityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eventCapacity.adapter = capacityAdapter

        eventCapacity.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null) {
                    selectedCapacity = parent.getItemAtPosition(position).toString().toLong()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        val priceAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, capacityNumbers)
        priceAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        eventPrice.adapter = priceAdapter

        eventPrice.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent != null) {
                    selectedPrice = parent.getItemAtPosition(position).toString().toDouble()
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Do nothing
            }
        }

        addEventButton.setOnClickListener {
            eventName = txtEventName.text.toString()
            eventDetails = txtEventDetails.text.toString()
            val check = checkFields(eventName, eventDetails, selectedCapacity, selectedPrice)
            if (check) {
                confirmDialog(eventName, hName, hId, hCategory, eventDetails, selectedCapacity, selectedPrice, hEvents)
            } else {alertDialog()
            }
        }

        backButtonAddEvents.setOnClickListener{
            dialog!!.dismiss()
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.MyDialogTheme)
    }

    fun checkFields(txtEventName: String, txtEventDetails: String, eventCapacity: Long, eventPrice: Double): Boolean {
        return !(txtEventName.isBlank() || txtEventDetails.isBlank() || eventCapacity == 0L || eventPrice == 0.0)
    }

    fun alertDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Error: Empty Field(s)")
            .setIcon(R.drawable.error_icon)
            .setCancelable(true)
            .setMessage("Please fill in all fields.")
            .create()
            .show()
    }

    fun successDialog() {
        AlertDialog.Builder(requireContext()) // Use context!! instead of requireContext()
            .setTitle("Success")
            .setIcon(R.drawable.check_icon)
            .setCancelable(true)
            .setMessage("Event Added Successfully.")
            .setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
                this@AddEventsFragment.dismiss()
            }
            .create()
            .show()
    }

    fun confirmDialog(eventName: String, hostName: String, hostId: String, hostCategory: String, eventDetails: String, selectedCapacity: Long, selectedPrice: Double, hostEvents: Int) {
        AlertDialog.Builder(requireContext())
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
                Toast.makeText(requireContext(), "Adding of Event Unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
        val hostActiveMap = mutableMapOf<String, Any>()
        hostActiveMap["hostEvents"] = hostEvents + 1
        hostActiveReference.child(hostId).updateChildren(hostActiveMap)
    }

}*/
