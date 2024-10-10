package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class HostLogin : AppCompatActivity() {

    lateinit var backButtonLP: Button
    lateinit var signUpHyperLink: TextView
    lateinit var loginButton: Button
    lateinit var editTextEmail: EditText
    lateinit var editTextPassword: EditText
    lateinit var hostList: ArrayList<Host>

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("Hosts")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_host_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hostLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        hostList = ArrayList<Host>()
        backButtonLP = findViewById(R.id.backButtonHostLP)
        signUpHyperLink = findViewById(R.id.hostSignUpHyperLink)
        loginButton = findViewById(R.id.hostLoginButton)
        editTextEmail = findViewById(R.id.hostEditTextTextEmailAddress2)
        editTextPassword = findViewById(R.id.hostEditTextTextPassword3)

        backButtonLP.setOnClickListener {
            val intent = Intent(this@HostLogin, addHostActivity::class.java)
            startActivity(intent)
        }

        signUpHyperLink.setOnClickListener {
            val intent = Intent(this@HostLogin, addHostActivity::class.java)
            startActivity(intent)
        }

        loginButton.setOnClickListener {
            retrieveDataFromDatabase()


        }
    }

    fun retrieveDataFromDatabase() {val etEmail = editTextEmail.text.toString()
        val etPassword = editTextPassword.text.toString()

        myReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var hostFound = false
                for (eachHost in snapshot.children) {
                    val host = eachHost.getValue(Host::class.java)
                    if (host != null && host.hostEmail == etEmail) {
                        hostFound = true
                        if (host.hostPassword == etPassword) {
                            // Login successful
                            val hostLoginIntent = Intent(this@HostLogin, HostDashboard::class.java)
                            hostLoginIntent.putExtra("hostId", host.hostId)
                            hostLoginIntent.putExtra("hostEvents", host.hostEvents)
                            hostLoginIntent.putExtra("hostName", host.hostName)
                            hostLoginIntent.putExtra("hostClicks", host.hostClicks)
                            hostLoginIntent.putExtra("hostCategory", host.hostCategory)
                            startActivity(hostLoginIntent)
                            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                            editTextEmail.text.clear()
                            editTextPassword.text.clear()
                        } else {
                            Toast.makeText(applicationContext, "Incorrect Password. Try again", Toast.LENGTH_SHORT).show()
                        }
                        break // Exit the loop once the host is found
                    }
                }

                if (!hostFound) {
                    Toast.makeText(applicationContext, "Incorrect Email. Try again", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("HostLogin", "Database error: ${error.message}")
                Toast.makeText(applicationContext, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}