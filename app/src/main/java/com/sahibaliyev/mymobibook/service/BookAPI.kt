package com.sahibaliyev.mymobibook.service


import com.sahibaliyev.mymobibook.model.BookModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Url

interface BookAPI {
    //https://raw.githubusercontent.com/Bysako/JSONVeriMobiBook/main/book.json

    @GET("Bysako/JSONVeriMobiBook/main/book.json")

    fun getData() :Call<List<BookModel>>



    @GET
    fun downloadPdfFile(@Url pdfUrl: String) :Call<ResponseBody>
}