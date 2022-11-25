package com.example.healthcode

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SignupActivity:AppCompatActivity() {
    lateinit var mAuth:FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        supportActionBar?.hide()
        mAuth=FirebaseAuth.getInstance()
        val btnSignup=findViewById<TextView>(R.id.btn_signUp)
        btnSignup.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val email=findViewById<EditText>(R.id.et_email_signup).text.toString().trim()
        val password=findViewById<EditText>(R.id.et_password_signup).text.toString().trim()
        if (email.isEmpty()||password.isEmpty()){
            Toast.makeText(this,"All fields required",Toast.LENGTH_SHORT).show()
        }
        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Password cannot be empty",Toast.LENGTH_SHORT).show()
        }
        else{
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                if (it.isSuccessful){
                    Toast.makeText(this,"Registered Successfully",Toast.LENGTH_SHORT).show()
                    sendEmailVerification()
//                    startActivity(Intent(this,SigninActivity::class.java))
                }
                else{
                    Toast.makeText(this,"Registration Unsuccessful",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun sendEmailVerification() {
        val firebaseUser=mAuth.currentUser
        if(firebaseUser!=null){//user exists
            firebaseUser.sendEmailVerification().addOnCompleteListener{
                Toast.makeText(this,"Verification link sent to registered Email id",Toast.LENGTH_SHORT).show()
                mAuth.signOut()
                finish()
                startActivity(Intent(this,SigninActivity::class.java))
            }
        }
        else{
            Toast.makeText(this,"Verification Failed",Toast.LENGTH_SHORT).show()
        }
    }
}