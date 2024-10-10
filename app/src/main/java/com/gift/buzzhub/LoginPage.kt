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
import com.gift.buzzhub.databinding.ActivityLoginPageBinding
import com.google.firebase.auth.FirebaseAuth

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hostLogin)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        backButtonLP = findViewById(R.id.backButtonHostLP)
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
                signInWithFirebase(userEmail, userPassword)
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
    fun signInWithFirebase(userEmail:String,userPassword:String) {

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
}