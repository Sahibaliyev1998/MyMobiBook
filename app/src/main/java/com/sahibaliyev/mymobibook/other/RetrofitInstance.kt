package com.sahibaliyev.mymobibook.other

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {

    companion object{

        fun getRetrofitInstance():Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val clientBuilder = OkHttpClient.Builder().addInterceptor(interceptor)
            return Retrofit.Builder()
                .client(clientBuilder.build())
                .baseUrl("https://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}