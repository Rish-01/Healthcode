package com.example.healthcode

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class SigninActivity:AppCompatActivity() {
    lateinit var mAuth: FirebaseAuth
    private lateinit var firebaseUser: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        supportActionBar?.hide()
        mAuth=FirebaseAuth.getInstance()
        if(mAuth.currentUser!=null){
            finish()
            startActivity(Intent(this,DashboardActivity::class.java))
        }
        val signupBtn=findViewById<TextView>(R.id.tv_signUp)
        signupBtn.setOnClickListener{
            startActivity(Intent(this,SignupActivity::class.java))
        }
        val forgotBtn=findViewById<TextView>(R.id.tv_forget_password)
        forgotBtn.setOnClickListener {
            startActivity(Intent(this,ForgotActivity::class.java))
        }
        val btnSignin=findViewById<AppCompatButton>(R.id.btn_signIn)
        btnSignin.setOnClickListener{
            val email=findViewById<EditText>(R.id.et_email_signTn).text.toString().trim()
            val password=findViewById<EditText>(R.id.et_password_signIn).text.toString().trim()
            if(email.isEmpty()||password.isEmpty())
                Toast.makeText(this,"All field required", Toast.LENGTH_SHORT).show()
            else {
                // TODO: get user details for authentication from firebase
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        Toast.makeText(this, "Logged in Successfully", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, DashboardActivity::class.java))
                    } else Toast.makeText(this, "Account doesn't exist", Toast.LENGTH_SHORT).show()
                }
            }
//            startActivity(Intent(this,DashboardActivity::class.java))
        }

    }
    private fun checkEmailVerification() {
        firebaseUser=mAuth?.currentUser!!
        if(firebaseUser?.isEmailVerified!!){
            Toast.makeText(this,"Logged In",Toast.LENGTH_SHORT).show()
            finish()//its significance?
            startActivity(Intent(this,DashboardActivity::class.java))
        }
        else{
            Toast.makeText(this,"Verify your Email first",Toast.LENGTH_SHORT).show()
            mAuth.signOut()
        }
    }
}


