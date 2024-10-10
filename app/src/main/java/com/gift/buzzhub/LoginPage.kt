package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gift.buzzhub.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPage : AppCompatActivity() {

    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val userMyReference: DatabaseReference = database.reference.child("Users")

    lateinit var binding: ActivityLoginPageBinding
    lateinit var backButtonLP: Button
    lateinit var signUpHyperLink: TextView
    val auth : FirebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLoginPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.userLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButtonLP = findViewById(R.id.backButtonLP)
        signUpHyperLink = findViewById(R.id.signUpHyperLink)

        backButtonLP.setOnClickListener {
            val intent = Intent(this@LoginPage, MainActivity::class.java)
            startActivity(intent)
        }
        signUpHyperLink.setOnClickListener {
            val intent = Intent(this@LoginPage, SignUpPage::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            val userEmail = binding.editTextTextEmailAddress2.text.toString()
            val userPassword = binding.editTextTextPassword3.text.toString()

            if (userEmail.isNotBlank() && userPassword.isNotBlank() && android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                retrieveDataFromDatabase()
            } else {
                if (userEmail.isBlank()) {
                    Toast.makeText(applicationContext,"Email field cannot be blank",Toast.LENGTH_SHORT).show()
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()) {
                    Toast.makeText(applicationContext,"Invalid Email Format",Toast.LENGTH_SHORT).show()
                }
                if (userPassword.isBlank()) {
                    Toast.makeText(applicationContext,"Password field cannot be blank",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }

    //Function to sign user in to firebase
    /*fun signInWithFirebase(userEmail:String,userPassword:String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(applicationContext,"Login is successful",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginPage,HomePage::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user, specifically an exception.
                    Toast.makeText(applicationContext,"Invalid login details",
                        Toast.LENGTH_SHORT).show()

                }
            }
        }
    */

    fun retrieveDataFromDatabase() {val etEmail = binding.editTextTextEmailAddress2.text.toString()
        val etPassword = binding.editTextTextPassword3.text.toString()

        userMyReference.addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var userFound = false
                for (eachUser in snapshot.children) {
                    val user = eachUser.getValue(Users::class.java)
                    if (user != null && user.userEmail == etEmail) {
                        userFound = true
                        if (user.userPassword == etPassword) {
                            // Login successful
                            val userLoginIntent = Intent(this@LoginPage, HomePage::class.java)
                            userLoginIntent.putExtra("userId", user.userId)
                            userLoginIntent.putExtra("userName", user.userName)
                            userLoginIntent.putExtra("userEmail", user.userEmail)
                            userLoginIntent.putExtra("userPassword", user.userPassword)
                            userLoginIntent.putExtra("userProvince", user.userProvince)
                            userLoginIntent.putExtra("userCity", user.userCity)
                            startActivity(userLoginIntent)
                            Toast.makeText(applicationContext, "Login Successful", Toast.LENGTH_SHORT).show()
                            binding.editTextTextEmailAddress2.text.clear()
                            binding.editTextTextPassword3.text.clear()
                        } else {
                            Toast.makeText(applicationContext, "Incorrect Password. Try again", Toast.LENGTH_SHORT).show()
                        }
                        break // Exit the loop once the host is found
                    }
                }

                if (!userFound) {
                    Toast.makeText(applicationContext, "Incorrect Email. Try again", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
                Log.e("LoginPage", "Database error: ${error.message}")
                Toast.makeText(applicationContext, "Database error: ${error.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}