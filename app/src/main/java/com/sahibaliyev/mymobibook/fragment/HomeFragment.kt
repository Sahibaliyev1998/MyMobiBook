package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.sahibaliyev.mymobibook.MVVM.HomeFragmentMVVM
import com.sahibaliyev.mymobibook.adapter.BookHomeAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel

class HomeFragment : Fragment(), BookHomeAdapter.Listener {


    private lateinit var binding: FragmentHomeBinding
    private lateinit var bookAdapter: BookHomeAdapter

    // private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: HomeFragmentMVVM


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        binding.rvHome.layoutManager = GridLayoutManager(context, 3)

        viewModel = ViewModelProviders.of(this)[HomeFragmentMVVM::class.java]


        //Adapterden checkbox
        //val bind = ItemHomeBinding.inflate(LayoutInflater.from(context))

        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    bookAdapter.filter.filter(s)
                } catch (e: Exception) {
                }
            }

            override fun afterTextChanged(p0: Editable?) {}
        })



        return binding.root
    }

    fun observeData() {
        viewModel.bookLiveData.observe(viewLifecycleOwner) {
            Log.d("MyTagHere", "observeData: ${it.size}")
            bookAdapter = BookHomeAdapter(it)
            binding.rvHome.adapter = bookAdapter
        }
    }


    override fun onItemClick(bookModel: BookModel) {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[HomeFragmentMVVM::class.java]


        viewModel.loadData()
        observeData()

    }
}