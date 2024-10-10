package com.gift.buzzhub

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.gift.buzzhub.databinding.ActivitySignUpPageBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.auth.User


class SignUpPage : AppCompatActivity() {

    lateinit var binding: ActivitySignUpPageBinding
    lateinit var signUpButton: Button
    lateinit var backButtonSP: Button
    lateinit var provinceSpinner2: Spinner
    lateinit var province:String
    lateinit var UserList:ArrayList<Users>
    val auth: FirebaseAuth= FirebaseAuth.getInstance()
    lateinit var loginHyperLink: TextView
    lateinit var eventHostHyperLink: TextView
    val database: FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("Users")

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
        provinceSpinner2 = findViewById(R.id.provinceSpinner2)

        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.provinces,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        provinceSpinner2.adapter = adapter

        provinceSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!=null){
                    val a:String = parent.getItemAtPosition(position).toString()
                    province = a
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

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
                addUserToDatabase()

            }
            else{
                Toast.makeText(applicationContext,"Your passwords do not match",
                    Toast.LENGTH_SHORT).show()
            }

        }

        eventHostHyperLink.setOnClickListener{
            val intent = Intent(this@SignUpPage,HostLogin::class.java)
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
            }
            else{
                Toast.makeText(applicationContext,
                    task.exception?.toString(),
                    Toast.LENGTH_SHORT).show()
            }

        }

    }

    fun addUserToDatabase(){
        val phoneNumber = if (binding.editTextTextPhone.text.toString().isNotBlank()) {
            binding.editTextTextPhone.text.toString().toLong()
        } else {
            0L
        }

        val id:String = myReference.push().key.toString()
        val check = checkFields(binding.editTextUserName.text.toString(),
            binding.editTextTextEmailAddress.text.toString(),
            binding.editTextTextPassword.text.toString(),
            binding.editTextTextPassword2.text.toString(),phoneNumber,province,
            binding.editTextTextCity.text.toString())

        if(check){
            val User = Users(id,binding.editTextUserName.text.toString(),binding.editTextTextEmailAddress.text.toString(),
                binding.editTextTextPassword.text.toString(),binding.editTextTextPhone.text.toString().toLong(),
                province,binding.editTextTextCity.text.toString())
            myReference.child(id).setValue(User).addOnCompleteListener { task ->

                if(task.isSuccessful){
                    Toast.makeText(applicationContext,
                        "Account successfully created",
                        Toast.LENGTH_SHORT).show()
                        signupWithFirebase(binding.editTextTextEmailAddress.text.toString(),
                            binding.editTextTextPassword.text.toString())
                    val loginIntent = Intent(this@SignUpPage,LoginPage::class.java)
                    startActivity(loginIntent)
                }
                else{
                    //Log.d("MyTag", "${task.exception?.toString()}")
                    Toast.makeText(applicationContext,
                        task.exception?.toString(),
                        Toast.LENGTH_SHORT).show()
                }
            }
        }

        else{
            var alertDialog1 = AlertDialog.Builder(this)

            alertDialog1.setTitle("Error: Empty Field(s)").setIcon(
                R.drawable.error_icon
            ).setCancelable(true).setMessage("Please fill in all fields.").create().show()
        }

    }

    fun checkFields(userName: String, userEmail: String, password: String, cPassword: String, phone:Long,province:String,city:String): Boolean {

        if (userName.isBlank() || userEmail.isBlank() || password.isBlank() || cPassword.isBlank() || province.isBlank() || city.isBlank() || phone == 0L
        ) {
            // At least one field isblank or null
            return false
        }
        // All fields are filled
        return true
    }


}