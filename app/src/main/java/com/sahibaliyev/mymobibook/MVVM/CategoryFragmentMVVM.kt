package com.sahibaliyev.mymobibook.MVVM

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.other.RetrofitInstance
import com.sahibaliyev.mymobibook.service.BookAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CategoryFragmentMVVM(application: Application) : BaseMVVM(application) {
    private lateinit var bookModel: ArrayList<BookModel>
    val bookLiveData = MutableLiveData<ArrayList<BookModel>>()

    fun loadData() {

        val retrofitInstance = RetrofitInstance.getRetrofitInstance()
        val service: BookAPI = retrofitInstance.create(BookAPI::class.java)
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
                            bookLiveData.postValue(it)
                        }
                    }
                }
            }
        })
    }

    
}