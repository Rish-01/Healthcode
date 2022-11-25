package com.example.healthcode

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.healthcode.databinding.FragmentCameraBinding

class CameraFragment : Fragment() {
    lateinit var binding: FragmentCameraBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCameraBinding.inflate(inflater,container,false)
        binding.fab.setOnClickListener{
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }
        return binding.root
    }
}