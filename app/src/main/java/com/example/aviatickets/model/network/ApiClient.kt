package com.example.aviatickets.model.network

import com.example.aviatickets.model.service.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://my-json-server.typicode.com/estharossa/fake-api-demo/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getInstance(): ApiService {
        return retrofit.create(ApiService::class.java)
    }

    /**
     * think about performing network request
     */
    val apiService: ApiService = retrofit.create(ApiService::class.java)
}
