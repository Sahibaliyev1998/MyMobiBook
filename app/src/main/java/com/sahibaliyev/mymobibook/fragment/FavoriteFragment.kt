package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.room.Room
import com.sahibaliyev.mymobibook.MVVM.FavoriteFragmentMVVM
import com.sahibaliyev.mymobibook.MVVM.HomeFragmentMVVM
import com.sahibaliyev.mymobibook.R
import com.sahibaliyev.mymobibook.databinding.FragmentFavoriteBinding
import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.util.AppDatabase

class FavoriteFragment : Fragment() {

    private lateinit var viewModel : FavoriteFragmentMVVM


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }




    override fun onViewCreated(view: View , savedInstanceState: Bundle?){
        super.onViewCreated(view , savedInstanceState)
        viewModel= ViewModelProviders.of(this)[FavoriteFragmentMVVM::class.java]
    }

}