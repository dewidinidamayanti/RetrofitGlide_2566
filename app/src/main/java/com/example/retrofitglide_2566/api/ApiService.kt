package com.example.retrofitglide_2566.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api/users?page=1") // Endpoint untuk mengambil list user halaman 1
    fun getListUsers(): Call<UserResponse>
}
