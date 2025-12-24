package com.example.retrofitglide_2566

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofitglide_2566.api.ApiConfig
import com.example.retrofitglide_2566.db.UserResponse
import com.example.retrofitglide_2566.db.UserAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_users)
        progressBar = findViewById(R.id.progress_bar)
        adapter = UserAdapter()

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        getDataFromApi()
    }

    private fun getDataFromApi() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE

        val client = ApiConfig.getApiService().getListUsers()

        client.enqueue(object : Callback<UserResponse> {
            override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
                progressBar.visibility = View.GONE
                if (response.isSuccessful) {
                    recyclerView.visibility = View.VISIBLE
                    val responseBody = response.body()
                    if (responseBody != null) {
                        adapter.setData(responseBody.data)
                    }
                } else {
                    Log.e("Main", "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                progressBar.visibility = View.GONE
                Log.e("Main", "onFailure: ${t.message}")
                Toast.makeText(this@MainActivity, "Gagal koneksi: ${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }
}