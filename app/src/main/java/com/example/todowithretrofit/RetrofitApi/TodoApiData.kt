package com.example.todowithretrofit.RetrofitApi

data class TodoApiData(
    val completed: Boolean,
    val id: Int,
    val title: String,
    val userId: Int
)