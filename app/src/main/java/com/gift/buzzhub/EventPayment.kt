package com.gift.buzzhub

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class EventPayment : AppCompatActivity() {

    lateinit var textViewEventPaymentName: TextView
    lateinit var textViewEventPaymentName3: TextView
    lateinit var textViewEventPaymentName4: TextView
    lateinit var textViewTicketNumber: TextView
    lateinit var minusBtn: FloatingActionButton
    lateinit var addButton2: FloatingActionButton
    lateinit var purchaseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_event_payment)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainEventPayment)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        textViewEventPaymentName = findViewById(R.id.textViewPaymentEventName)
        textViewEventPaymentName3 = findViewById(R.id.textViewPaymentEventName3)
        textViewEventPaymentName4 = findViewById(R.id.textViewPaymentEventName4)
        textViewTicketNumber = findViewById(R.id.textViewTicketNumber)
        minusBtn = findViewById(R.id.minusButton)
        addButton2 = findViewById(R.id.addButton2)
        purchaseButton = findViewById(R.id.purchaseBtn)

        textViewEventPaymentName.text = intent.getStringExtra("eventName")
        textViewEventPaymentName3.text = intent.getStringExtra("eventDetails")
        textViewEventPaymentName4.text = "R${intent.getDoubleExtra("eventPrice",0.0)}"

        var counter = 0
        minusBtn.setOnClickListener{

            if(counter > 0){
                counter --
                textViewTicketNumber.text = "${counter.toString()}"
            }
            else{
                textViewTicketNumber.text = "0"
            }
        }

        addButton2.setOnClickListener{
            counter ++
            textViewTicketNumber.text = "${counter.toString()}"
        }

        purchaseButton.setOnClickListener{
            val siteUrl = "https://pay.ozow.com/f45a2df7-88cb-4a67-a741-1e8dfbd67beb/bank-selection/"
            openWebPage(this@EventPayment, siteUrl)
        }


    }

    private fun openWebPage(context: Context, url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }
}