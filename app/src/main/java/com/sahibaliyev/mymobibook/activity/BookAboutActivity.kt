package com.sahibaliyev.mymobibook.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import com.sahibaliyev.mymobibook.databinding.ActivityBookAboutBinding
import com.sahibaliyev.mymobibook.databinding.ActivityBookAboutBinding.inflate
import com.sahibaliyev.mymobibook.other.fullScreen

class BookAboutActivity : AppCompatActivity() {
    private lateinit var  binding: ActivityBookAboutBinding


    private var bookId =""

    //constructor()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)

        fullScreen(window)

        runtimePermission()
        val pdf = intent.getStringExtra("pdf")




        binding.btnRead.setOnClickListener {
            val i = Intent(this, ReadBook::class.java)
            i.putExtra("bookId", bookId)
            i.putExtra("pdf", pdf)
            startActivity(i)
        }

        loadData()
    }

    fun loadData() {

        Glide.with(this)
            .load(intent.getStringExtra("image"))
            .into(binding.imgBook)

        binding.txtName.text = intent.getStringExtra("name")
        binding.txtCategory.text = intent.getStringExtra("category")
        binding.txtDescription.text = intent.getStringExtra("description")
    }

    fun runtimePermission(){

        Dexter
            .withContext(this)
            .withPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(response: PermissionGrantedResponse) {

                }

                override fun onPermissionDenied(response: PermissionDeniedResponse) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    permission: PermissionRequest,
                    token: PermissionToken
                ) {
                    token.continuePermissionRequest()
                }
            })
            .check()
    }

}