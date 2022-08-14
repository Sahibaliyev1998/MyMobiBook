package com.sahibaliyev.mymobibook.activity

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.sahibaliyev.mymobibook.databinding.ActivityMainBinding
import com.sahibaliyev.mymobibook.model.BookModel

class MainActivity : AppCompatActivity() {

    //
    private lateinit var  binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



    }

    fun permission(){
        if ( ContextCompat.checkSelfPermission(this ,android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this , arrayOf(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) , 1  )
        }
    }


}