package com.example.retrofitglide_2566.db

data class UserResponse(
    val data: List<User>
)

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String // Ini berisi URL gambar
)
