package com.sahibaliyev.mymobibook.MVVM

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.sahibaliyev.mymobibook.model.BookModel
//import com.sahibaliyev.mymobibook.model.FavoriteEntity
import com.sahibaliyev.mymobibook.other.RetrofitInstance
import com.sahibaliyev.mymobibook.service.BookAPI
import com.sahibaliyev.mymobibook.util.AppDatabase
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragmentMVVM(application: Application) : BaseMVVM(application) {
    val bookLiveData = MutableLiveData<ArrayList<BookModel>>()
    lateinit var bookModels: ArrayList<BookModel>

    /*fun dataSave(favoriteList: List<FavoriteEntity>) {

        launch {
            val dao = AppDatabase(getApplication()).favoriteDao()
            dao.deleteAllFavorite()
            dao.insertAll(*favoriteList.toTypedArray())
        }
    }*/

    fun saveBook(book: BookModel) {
        val dao = AppDatabase(getApplication()).favoriteDao()
        launch(coroutineContext) {
            dao.insert(book)
            Log.d("MyTagHere", "saveBook: ${dao.getAll().size}")
        }
    }

    fun removeBook(book: BookModel) {
        val dao = AppDatabase(getApplication()).favoriteDao()
        launch(coroutineContext) {
            dao.delete(book)
            Log.d("MyTagHere", "saveBook: ${dao.getAll().size}")
        }
    }

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
                        bookModels = ArrayList(it)
                        bookModels.let {
                            bookLiveData.postValue(it)
                        }
                    }
                }
            }
        })
    }


}