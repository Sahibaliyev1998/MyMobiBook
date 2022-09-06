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
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahibaliyev.mymobibook.MVVM.CategoryFragmentMVVM
import com.sahibaliyev.mymobibook.adapter.BookCategoryAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentCategoryBinding
class CategoryFragment : Fragment() {

    private lateinit var binding: FragmentCategoryBinding
    private lateinit var bookAdapter: BookCategoryAdapter
    private lateinit var viewModel: CategoryFragmentMVVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.rvCategory.layoutManager = LinearLayoutManager(context)



        binding.etSearchCategory.addTextChangedListener(object : TextWatcher {
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
            bookAdapter = BookCategoryAdapter(it)
            binding.rvCategory.adapter = bookAdapter
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this)[CategoryFragmentMVVM::class.java]

        viewModel.loadData()
        observeData()
    }


}