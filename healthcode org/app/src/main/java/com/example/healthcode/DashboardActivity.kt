package com.example.healthcode

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import java.io.File


class DashboardActivity:AppCompatActivity() {
    private var mCount:Int=-1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        supportActionBar?.hide()
        val homeFragment= HomeFragment()
        val cameraFragment= CameraFragment()
        val monthliesFragment= MonthliesFragment()
        val stepsFragment= StepsFragment()

        setCurrentFragment(homeFragment)

        val myBottomNavigationView = findViewById<ChipNavigationBar>(R.id.nav_view)
        myBottomNavigationView.setItemSelected(R.id.home)
        myBottomNavigationView.setOnItemSelectedListener { item ->
            when (item) {
                R.id.home -> {
                    mCount=1
                    Log.i("NavBar", "Home pressed")
                    setCurrentFragment(homeFragment)
                }
                R.id.camera -> {
                    mCount=0
                    Log.i("NavBar", "Profile pressed")
                    setCurrentFragment(cameraFragment)
//                    val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//                    startActivity(intent)
                }


                R.id.monthlies -> {

                    mCount=0
                    setCurrentFragment(monthliesFragment)

                }

                R.id.steps -> {
                    mCount=0
                    setCurrentFragment(stepsFragment)
                }
                else -> {
                    Log.i("NavBar", "Error?")

                }
            }
        }
    }

    override fun onBackPressed() {
        if(mCount==0)
            goHomeFragment()
        else{
            super.onBackPressed()
        }

        mCount++
    }


    private fun setCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment_activity_dashboard, fragment)
            commit()
        }
    private fun goHomeFragment(){
        val fragmentHome = HomeFragment()
        findViewById<ChipNavigationBar>(R.id.nav_view)?.setItemSelected(R.id.home)
        supportFragmentManager.beginTransaction().apply {
            add(R.id.nav_host_fragment_activity_dashboard,fragmentHome)
            commit()
        }
    }
}