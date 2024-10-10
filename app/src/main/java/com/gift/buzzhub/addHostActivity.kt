package com.gift.buzzhub

import android.content.Intent
import android.graphics.Color
import android.os.Build
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
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class addHostActivity : AppCompatActivity() {
    lateinit var spinner: Spinner
    lateinit var txtEmail:TextInputEditText
    lateinit var txtPhone:TextInputEditText
    lateinit var txtCity:TextInputEditText
    lateinit var txtName:TextInputEditText
    lateinit var password1:TextInputEditText
    lateinit var password2:TextInputEditText
    lateinit var loginHyperLink: TextView
    lateinit var btnHostSignUp:Button
    lateinit var btnBack:Button
    lateinit var catSpinner: Spinner
    lateinit var province:String
    lateinit var category:String

    val database:FirebaseDatabase = FirebaseDatabase.getInstance()
    val myReference: DatabaseReference = database.reference.child("Hosts")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add)
        //status bar change colour to buzzhub Green (Sam)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            window.statusBarColor= Color.parseColor("#98FB98")
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.mainHost)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        // Binding spinner component to spinner object
        spinner = findViewById(R.id.provinceSpinner)
        loginHyperLink = findViewById(R.id.loginTextView)
        password1 = findViewById(R.id.password1)
        password2 = findViewById(R.id.password2)
        catSpinner = findViewById(R.id.categoriesSpinner)
        txtCity = findViewById(R.id.txtCity)
        var userName:String
        var userEmail:String
        var password:String
        var cPassword:String

        var city:String
        var phoneNumber:Long



        var catAdapter = ArrayAdapter.createFromResource(this,
            R.array.Categories,
            android.R.layout.simple_spinner_item)

        catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        catSpinner.adapter = catAdapter

        var adapter = ArrayAdapter.createFromResource(
            this,
            R.array.provinces,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter



        txtPhone = findViewById(R.id.txtPhone)
        txtName = findViewById(R.id.txtName)
        txtEmail = findViewById(R.id.txtEmail)
        btnHostSignUp = findViewById(R.id.btnHostSignUp)
        btnBack = findViewById(R.id.btnBack)


        btnBack.setOnClickListener{

            val addHostIntent= Intent(this@addHostActivity,SignUpPage::class.java)
            startActivity(addHostIntent)



        }
        btnHostSignUp.setOnClickListener {
                addHostToDatabase()


            }

            loginHyperLink.setOnClickListener{
                val addHostIntent= Intent(this@addHostActivity,HostLogin::class.java)
                startActivity(addHostIntent)
            }

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!=null){
                    val a:String = parent.getItemAtPosition(position).toString()
                    province = a
                }

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        catSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if(parent!=null){
                    val a:String = parent.getItemAtPosition(position).toString()
                    category = a
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        }

    fun addHostToDatabase(){
        val hostName = txtName.text.toString()
        val hostEmail = txtEmail.text.toString()
        val password = password1.text.toString()
        val cPassword = password2.text.toString()
        val city = txtCity.text.toString()
        val phoneNumber = if (txtPhone.text.toString().isNotBlank()) {
            txtPhone.text.toString().toLong()
        } else {
            0L
        }

        val id:String = myReference.push().key.toString()
        val check = checkFields(hostName,hostEmail,password,cPassword,province,category,city,phoneNumber)

        if(check){
            val host = Host(id,hostName,hostEmail,password,province,category,city,phoneNumber,0,0)
            myReference.child(id).setValue(host).addOnCompleteListener { task ->

                if(task.isSuccessful){
                    Toast.makeText(applicationContext,
                        "Account successfully created",
                        Toast.LENGTH_SHORT).show()
                    val addHostIntent = Intent(this@addHostActivity,HostLogin::class.java)
                    startActivity(addHostIntent)
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





    fun alert(allValid:Boolean){
        var alertDialog = AlertDialog.Builder(this)

        if(allValid){

            alertDialog.setTitle("Success").setIcon(R.drawable.check_icon
            ).setCancelable(true).setMessage("Account Created Successfully")
        }
        else{

            alertDialog.setTitle("Error").setIcon(R.drawable.error_icon
            ).setCancelable(true).setMessage("Account Not Created Successfully")

        }
        alertDialog.create().show()


    }

    fun checkFields(userName: String, userEmail: String, password: String, cPassword: String,
                    province: String, category: String, city: String, phone: Long): Boolean {

        if (userName.isBlank() || userEmail.isBlank() || password.isBlank() || cPassword.isBlank() ||
            province.isBlank() || category.isBlank() || city.isBlank() || phone == 0L
        ) {
            // At least one field isblank or null
            return false
        }
        // All fields are filled
        return true
    }




}