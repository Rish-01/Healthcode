package com.example.healthcode

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.example.healthcode.databinding.FragmentCameraBinding
import android.content.ContentResolver
import android.graphics.Bitmap
import android.net.Uri

class CameraFragment : Fragment() {
    lateinit var binding: FragmentCameraBinding
    private val RESULT_LOAD_IMAGE = 1
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= FragmentCameraBinding.inflate(inflater,container,false)
        binding.fab.setOnClickListener{
            val intent = Intent("android.media.action.IMAGE_CAPTURE")
            startActivity(intent)
        }
    binding.postImage.setOnClickListener{
        val i = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(i, RESULT_LOAD_IMAGE)
    }


        return binding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            val selectedImage = data.data
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? =
                activity?.contentResolver?.query(selectedImage!!, filePathColumn, null, null, null)
            cursor?.moveToFirst()
            val columnIndex = cursor?.getColumnIndex(filePathColumn[0])
            val picturePath = cursor?.getString(columnIndex!!)
            cursor?.close()
            var image: Bitmap=MediaStore.Images.Media.getBitmap(activity?.contentResolver, Uri.parse(selectedImage.toString()))
            binding.postImage.setImageBitmap(image)
        }
    }




}