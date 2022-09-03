package com.sahibaliyev.mymobibook.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.sahibaliyev.mymobibook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //
    private lateinit var  binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }

/**
    fun permission() {
        if (ContextCompat.checkSelfPermission(
    this,
    android.Manifest.permission.READ_EXTERNAL_STORAGE
    ) != PackageManager.PERMISSION_GRANTED
    ) {
    ActivityCompat.requestPermissions(
    this,
    arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
    1
    )
    }
    }*/

    /** fun elebelemap(){
    val elebelelist = mutableListOf<BookModel>()

    val elebelemapp = elebelelist.associateBy{
    it.category
    }

    elebelelist.map {
    it.category
    }
    }*/

}