package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gift.buzzhub.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class LoginPage : AppCompatActivity() {

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButtonLP = findViewById(R.id.backButtonLP)
        signUpHyperLink = findViewById(R.id.signUpHyperLink)

        backButtonLP.setOnClickListener {
            var intent = Intent(this@LoginPage, MainActivity::class.java)
            startActivity(intent)
        }
        signUpHyperLink.setOnClickListener {
            var intent = Intent(this@LoginPage, SignUpPage::class.java)
            startActivity(intent)
        }

        binding.loginButton.setOnClickListener{
            //login button being clicked causes email and password input to be stored into both variables below
            val userEmail = binding.editTextTextEmailAddress2.text.toString()
            val userPassword = binding.editTextTextPassword3.text.toString()
            //Sign In with Firebase function logs user in with email and password if successful
            signInWithFirebase(userEmail,userPassword)

        }
    }

    //Function to sign user in to firebase
    fun signInWithFirebase(userEmail:String,userPassword:String) {

        auth.signInWithEmailAndPassword(userEmail, userPassword)
            .addOnCompleteListener(this) { task ->

                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information

                    Toast.makeText(applicationContext,"Login is successful",
                        Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginPage,HomePage::class.java)
                    startActivity(intent)


                } else {
                    // If sign in fails, display a message to the user, specifically an exception.
                    Toast.makeText(applicationContext,task.exception.toString()
                        ,Toast.LENGTH_SHORT).show()

                }
            }

    }
}