package com.example.todowithretrofit.RetrofitApi

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface  TodoApi {

    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoApiData>>
}