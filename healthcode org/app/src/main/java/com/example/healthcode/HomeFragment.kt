package com.example.healthcode

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.healthcode.databinding.FragmentHomeBinding
import de.hdodenhof.circleimageview.CircleImageView

class HomeFragment : Fragment() {
    lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentHomeBinding.inflate(inflater,container,false)
        binding.userImage.setOnClickListener{
            Toast.makeText(activity,"Clicked",Toast.LENGTH_SHORT).show()
            startActivity(Intent(context,SettingsActivity::class.java))
        }
        return binding.root
    }
}