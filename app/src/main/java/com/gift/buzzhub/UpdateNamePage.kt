package com.gift.buzzhub

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateNamePage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference

    lateinit var currentNameEditText: EditText
    lateinit var newNameEditText: EditText
    lateinit var changeNameButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_name_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference


        currentNameEditText = findViewById(R.id.currentNameEditText)
        newNameEditText = findViewById(R.id.newNameEditText)
        changeNameButton = findViewById(R.id.changeNameButton)

        changeNameButton.setOnClickListener{
            val currentName = currentNameEditText.text.toString()
            val newName = newNameEditText.text.toString()

            updateName(newName, currentName)


        }

    }
    private fun updateName(newName: String, currentName: String) {
        val userId = auth.currentUser?.uid

        if (userId != null) {
            val userRef = database.child("Users").child(userId)
            userRef.child("name").get().addOnSuccessListener { dataSnapshot ->
                val storedName = dataSnapshot.getValue(String::class.java)

                if (storedName == currentName) {
                    userRef.child("name").setValue(newName)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Name updated successfully", Toast.LENGTH_SHORT).show()
                        }
                        .addOnFailureListener { e ->
                            Toast.makeText(this, "Failed to update name: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                } else {
                    Toast.makeText(this, "Current name does not match", Toast.LENGTH_SHORT).show()
                }
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Failed to retrieve user data: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "User not authenticated", Toast.LENGTH_SHORT).show()
        }
    }
}