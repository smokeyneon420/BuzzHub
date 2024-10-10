package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gift.buzzhub.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class SignUpPage : AppCompatActivity() {

    lateinit var binding: ActivitySignUpPageBinding
    lateinit var signUpButton: Button
    lateinit var backButtonSP: Button
    val auth: FirebaseAuth= FirebaseAuth.getInstance()
    lateinit var loginHyperLink: TextView
    lateinit var eventHostHyperLink: TextView
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("MyUsers")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUpPageBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.SignUp)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        backButtonSP = findViewById(R.id.backButtonSP)
        loginHyperLink = findViewById(R.id.loginHyperLink)
        eventHostHyperLink = findViewById(R.id.eventHostHyperLink)
        signUpButton = findViewById(R.id.signUpButton)

        backButtonSP.setOnClickListener {
            var intent =Intent(this@SignUpPage, MainActivity::class.java)
            startActivity(intent)
        }
        loginHyperLink.setOnClickListener{
            var intent = Intent(this@SignUpPage, LoginPage::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener{
            //confirms password fields match
           val c = confirmPassword(binding.editTextTextPassword.text.toString(),
                binding.editTextTextPassword2.text.toString())
            if (c == true) {
                //once password fields match the signupWithFirebase function gets called
                signupWithFirebase(binding.editTextTextEmailAddress.text.toString(),
                    binding.editTextTextPassword.text.toString())
                //intent helps to move user to Login Page after signing in.
                val intent = Intent(this@SignUpPage,LoginPage::class.java)
                startActivity(intent)

            }
            else{
                Toast.makeText(applicationContext,"Your passwords do not match",
                    Toast.LENGTH_SHORT).show()
            }

        }

        eventHostHyperLink.setOnClickListener{
            val intent = Intent(this@SignUpPage,addHostActivity::class.java)
            startActivity(intent)
        }

    }

    //function to confirm if password field 1 is equal to password field 2
    fun confirmPassword(password:String,cPassword:String): Boolean{
        if (password == cPassword) {
            return true
        }
        else{
            return false
        }
    }

    //function that signs up user to firebase Authentication Database NOT Realtime Database
    fun signupWithFirebase(userEmail:String, userPassword:String){
        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener{ task ->

            if(task.isSuccessful){
                Toast.makeText(applicationContext,
                    "You have successfully created your account!",
                    Toast.LENGTH_SHORT).show()

            }
            else{
                Toast.makeText(applicationContext,
                    task.exception?.toString(),
                    Toast.LENGTH_SHORT).show()
            }

        }

    }
}