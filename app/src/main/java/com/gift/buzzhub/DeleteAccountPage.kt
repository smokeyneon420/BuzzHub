package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class DeleteAccountPage : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    lateinit var deleteText : Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_delete_account_page)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        deleteText = findViewById(R.id.deleteText)
        deleteText.setOnClickListener {
            showConfirmationDialog()

        }
    }

    private fun showConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you want to delete your account? This action cannot be undone.")


        builder.setPositiveButton("Yes") { dialog, _ ->
            deleteAccount()
            dialog.dismiss()
        }
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
    private fun navigateToHomePage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()

    }

    private fun deleteAccount() {
        val user = auth.currentUser

        user?.let {
            it.delete().addOnCompleteListener { task ->
                if(task.isSuccessful) {
                    navigateToHomePage()
                    Toast.makeText(this, "Account deleted successfully", Toast.LENGTH_SHORT).show()

                }
                else {
                    Toast.makeText(this, "Failed to delete account", Toast.LENGTH_SHORT).show()
                }
            }
        } ?: run {
            Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
        }

    }

}