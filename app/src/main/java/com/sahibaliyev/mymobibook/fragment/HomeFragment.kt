package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.util.Base64
import android.util.Base64.encodeToString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.sahibaliyev.mymobibook.adapter.BookHomeAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.service.BookAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment(), BookHomeAdapter.Listener {


    private val BASE_URL = "https://raw.githubusercontent.com/"
    private var bookModel: ArrayList<BookModel>? = null
    private lateinit var binding: FragmentHomeBinding
    private var bookAdapter: BookHomeAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.homeRV.layoutManager = GridLayoutManager(context, 3)

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
                        bookModel?.let {
                            bookAdapter = BookHomeAdapter(it)
                            binding.homeRV.adapter = bookAdapter
                        }
                    }
                }
            }
        })
    }


    override fun onItemClick(bookModel: BookModel) {}
}