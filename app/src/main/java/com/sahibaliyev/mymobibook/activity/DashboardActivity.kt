package com.sahibaliyev.mymobibook.activity

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sahibaliyev.mymobibook.R
import com.sahibaliyev.mymobibook.databinding.ActivityDashboardBinding
import com.sahibaliyev.mymobibook.fragment.CategoryFragment
import com.sahibaliyev.mymobibook.fragment.FavoriteFragment
import com.sahibaliyev.mymobibook.fragment.HomeFragment
import com.sahibaliyev.mymobibook.fragment.ProfilFragment
import com.sahibaliyev.mymobibook.other.fullScreen

class DashboardActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDashboardBinding

    private val homeFragment = HomeFragment()
    private val categoryFragment = CategoryFragment()
    private val favoriteFragment = FavoriteFragment()
    private val profilFragment = ProfilFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fullScreen(window)
        binding.bnbDashboard.setItemSelected(R.id.home , true)

        frm()



    }


    fun frm(){
        nob(homeFragment)

        binding.bnbDashboard.setOnItemSelectedListener  {
            when (it){
                R.id.home -> nob(homeFragment)
                R.id.category -> nob(categoryFragment)
                R.id.favorit -> nob(favoriteFragment)
                R.id.profil -> nob(profilFragment)
            }
        }
    }

    fun nob (fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment , fragment)
            commit()
        }
    }
}