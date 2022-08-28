package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.sahibaliyev.mymobibook.MVVM.ProfileFragmentMVVM
import com.sahibaliyev.mymobibook.R

class ProfilFragment : Fragment() {

    private lateinit var viewModel: ProfileFragmentMVVM

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profil, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[ProfileFragmentMVVM::class.java]
    }
}