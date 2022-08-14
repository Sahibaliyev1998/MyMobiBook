package com.sahibaliyev.mymobibook.other

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.sahibaliyev.mymobibook.R
import com.sahibaliyev.mymobibook.activity.DashboardActivity
import com.sahibaliyev.mymobibook.activity.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        supportActionBar?.hide()

        fullScreen(window)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreen  , DashboardActivity::class.java))
            finish()
        },1100)

    }

}