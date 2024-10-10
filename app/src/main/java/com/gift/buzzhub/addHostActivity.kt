package com.gift.buzzhub

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText



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

            val intent= Intent(this@addHostActivity,SignUpPage::class.java)
            startActivity(intent)



        }
        btnHostSignUp.setOnClickListener {
            if (txtEmail.text.toString() == "" ) {
                //alert(false)

                var alertDialog1 = AlertDialog.Builder(this)

                alertDialog1.setTitle("Error: Empty Field(s)").setIcon(
                    R.drawable.error_icon
                ).setCancelable(true).setMessage("Please fill in all fields.").create().show()


            } else {
                        alert(true)

                }

            }

        loginHyperLink.setOnClickListener{
            val intent= Intent(this@addHostActivity,HostLogin::class.java)
            startActivity(intent)
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
}