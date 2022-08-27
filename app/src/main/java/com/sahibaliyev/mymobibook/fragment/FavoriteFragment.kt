package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.sahibaliyev.mymobibook.databinding.FragmentFavoriteBinding
import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.util.AppDatabase

class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(layoutInflater)
        binding.rvFavorite.layoutManager = GridLayoutManager(context, 3)




        //favData()
        return binding.root
    }

    fun favData(){

        val db = context?.let {
            Room.databaseBuilder(
                it.applicationContext,
                AppDatabase::class.java, "database-name"
            ).build()
        }


        val favDao = db?.favoriteDao()
        val fav: List<FavoriteEntity> = favDao!!.getAll()
    }

}