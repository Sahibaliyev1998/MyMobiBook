package com.sahibaliyev.mymobibook.MVVM

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.androidnetworking.interceptors.HttpLoggingInterceptor
import com.sahibaliyev.mymobibook.model.BookModel
import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.service.BookAPI
import com.sahibaliyev.mymobibook.util.AppDatabase
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeFragmentMVVM(application: Application) : BaseMVVM(application) {
    val bookLiveData = MutableLiveData<ArrayList<BookModel>>()


    private val BASE_URL = "https://raw.githubusercontent.com/"
    private lateinit var bookModel: ArrayList<BookModel>


    fun dataSave(favoriteList: List<FavoriteEntity>) {

        launch {
            val dao = AppDatabase(getApplication()).favoriteDao()
            dao.deleteAllFavorite()
            dao.insertAll(*favoriteList.toTypedArray())
        }
    }

    fun loadData() {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val clientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clientBuilder.build())
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
                            bookLiveData.postValue(it)
                        }
                    }
                }
            }
        })
    }


}