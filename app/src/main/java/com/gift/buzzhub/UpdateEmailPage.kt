
package com.gift.buzzhub

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateEmailPage : AppCompatActivity() {
    lateinit var currentEmailEditText: EditText
    lateinit var confirmEmailEditText: EditText
    lateinit var newEmailEditText: EditText
    lateinit var currentPasswordEditText: EditText
    lateinit var changeEmailButton: Button

    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_email_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().reference
        sharedPreferences = getSharedPreferences("email_update_prefs", Context.MODE_PRIVATE)

        currentEmailEditText = findViewById(R.id.currentEmailEditText)
        confirmEmailEditText = findViewById(R.id.confirmEmailEditText)
        newEmailEditText = findViewById(R.id.newEmailEditText)
        changeEmailButton = findViewById(R.id.changeEmailButton)
        currentPasswordEditText = findViewById(R.id.currentPasswordEditText)

        changeEmailButton.setOnClickListener {
            val currentEmail = currentEmailEditText.text.toString().trim()
            val confirmEmail = confirmEmailEditText.text.toString().trim()
            val newEmail = newEmailEditText.text.toString().trim()
            val currentPassword = currentPasswordEditText.text.toString()

            //changeEmail(currentEmail, confirmEmail, newEmail, currentPassword)
            if (currentEmail.isEmpty() && confirmEmail.isEmpty() && newEmail.isEmpty() && currentPassword.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show()
            } else {
                changeEmail(currentEmail, confirmEmail, newEmail, currentPassword)
            }
        }
    }

    private fun changeEmail(
        currentEmail: String,
        confirmEmail: String,
        newEmail: String,
        currentPassword: String
    ) {
        val currentUser = auth.currentUser

        currentUser?.let { user ->
            if (user.email == currentEmail) {
                if (newEmail == confirmEmail) {
                    val credential = EmailAuthProvider.getCredential(currentEmail, currentPassword)
                    user.reauthenticate(credential)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Send the verification email
                                user.verifyBeforeUpdateEmail(newEmail)
                                    .addOnCompleteListener { emailUpdateTask ->
                                        if (emailUpdateTask.isSuccessful) {
                                            Toast.makeText(
                                                this,
                                                "Verification email sent to $newEmail. Please verify it.",
                                                Toast.LENGTH_SHORT
                                            ).show()

                                        } else {
                                            Toast.makeText(
                                                this,
                                                "Failed to send email verification: ${emailUpdateTask.exception?.message}",
                                                Toast.LENGTH_SHORT
                                            ).show()
                                        }
                                    }
                            } else {
                                Toast.makeText(
                                    this,
                                    "Authentication failed: ${task.exception?.message}",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                } else {
                    Toast.makeText(
                        this,
                        "New email and confirmation email do not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(this, "Current email does not match", Toast.LENGTH_SHORT).show()
            }
        } ?: run {
            Toast.makeText(this, "No authenticated user found", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        currentUser?.reload()?.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                if (currentUser.isEmailVerified) {
                    val emailUpdateDone = sharedPreferences.getBoolean("email_update_done", false)

                    // Show the toast only if the email was just updated
                    if (!emailUpdateDone) {
                        updateEmailInDatabase(currentUser.uid, currentUser.email ?: "")
                        Toast.makeText(this, "Email updated successfully after verification", Toast.LENGTH_SHORT).show()

                        // Mark that the email has been updated and the message should not be shown again
                        sharedPreferences.edit().putBoolean("email_update_done", true).apply()
                    }
                }
            } else {
                Toast.makeText(this, "Failed to reload user: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateEmailInDatabase(userId: String, newEmail: String) {
        val userRef = database.child("users").child(userId).child("email")
        userRef.setValue(newEmail)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(
                        this,
                        "Email updated successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this,
                        "Failed to update email: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}

