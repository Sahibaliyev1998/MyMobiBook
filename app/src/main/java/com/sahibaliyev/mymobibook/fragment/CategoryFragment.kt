package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.sahibaliyev.mymobibook.MVVM.CategoryFragmentMVVM
import com.sahibaliyev.mymobibook.MVVM.HomeFragmentMVVM
import com.sahibaliyev.mymobibook.R
import com.sahibaliyev.mymobibook.adapter.BookCategoryAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentCategoryBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.service.BookAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoryFragment : Fragment() {

    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var bookModel: ArrayList<BookModel>
    private lateinit var binding: FragmentCategoryBinding
    private lateinit var bookAdapter: BookCategoryAdapter
    private lateinit var viewModel : CategoryFragmentMVVM

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCategoryBinding.inflate(layoutInflater)
        binding.rvCategory.layoutManager = LinearLayoutManager(context)



        binding.etSearchCategory.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                try {
                    bookAdapter.filter.filter(s)
                } catch (e: Exception) {

                }
            }

            override fun afterTextChanged(p0: Editable?) {

            }
        })
        loadData()



        return binding.root
    }



    fun loadData() {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service: BookAPI = retrofit.create(BookAPI::class.java)

        val call: Call<List<BookModel>> = service.getData()

        call.enqueue(object : Callback<List<BookModel>> {
            override fun onFailure(call: Call<List<BookModel>>, t: Throwable) {
                t.printStackTrace()
            }
            override fun onResponse(
                call: Call<List<BookModel>>,
                response: Response<List<BookModel>>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        bookModel = ArrayList(it)
                        bookModel.let {
                            bookAdapter = BookCategoryAdapter(it)
                            binding.rvCategory.adapter = bookAdapter
                        }
                    }
                }
            }
        })
    }

    override fun onViewCreated(view: View , savedInstanceState: Bundle?){
        super.onViewCreated(view , savedInstanceState)
        viewModel= ViewModelProviders.of(this)[CategoryFragmentMVVM::class.java]


    }


}