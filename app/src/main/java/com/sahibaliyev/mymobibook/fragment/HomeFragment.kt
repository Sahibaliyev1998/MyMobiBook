package com.sahibaliyev.mymobibook.fragment

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.sahibaliyev.mymobibook.adapter.BookHomeAdapter
import com.sahibaliyev.mymobibook.databinding.FragmentHomeBinding
import com.sahibaliyev.mymobibook.databinding.ItemHomeBinding
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.service.BookAPI
import com.sahibaliyev.mymobibook.util.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragment : Fragment(), BookHomeAdapter.Listener {


    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var bookModel: ArrayList<BookModel>
    private lateinit var binding: FragmentHomeBinding
    private lateinit var bookAdapter: BookHomeAdapter
    private lateinit var appDatabase: AppDatabase


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.rvHome.layoutManager = GridLayoutManager(context, 3)
//Adapterden checkbox
        val bind = ItemHomeBinding.inflate(LayoutInflater.from(context))



        binding.etSearch.addTextChangedListener(object : TextWatcher {
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



        bind.cbFavorit.setOnCheckedChangeListener { buttonView, isChecked ->
            appdata()
        }





        return binding.root
    }

    fun appdata() {

        val fav = FavoriteEntity()
        fav.id

        appDatabase = context?.let { AppDatabase.getAppDatabase(it) }!!
        appDatabase.favoriteDao().insertAll(fav)


    }

    private fun loadData() {
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
                            bookAdapter = BookHomeAdapter(it)
                            binding.rvHome.adapter = bookAdapter
                        }
                    }
                }
            }
        })
    }


    override fun onItemClick(bookModel: BookModel) {}
}