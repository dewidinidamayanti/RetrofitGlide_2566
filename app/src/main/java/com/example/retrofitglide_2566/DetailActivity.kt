package com.example.retrofitglide_2566

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val name = intent.getStringExtra("USER_NAME")
        val email = intent.getStringExtra("USER_EMAIL")
        val avatarUrl = intent.getStringExtra("USER_AVATAR_URL")

        val tvNameDetail: TextView = findViewById(R.id.tv_name_detail)
        val tvEmailDetail: TextView = findViewById(R.id.tv_email_detail)
        val ivAvatarDetail: ImageView = findViewById(R.id.iv_avatar_detail)

        tvNameDetail.text = name
        tvEmailDetail.text = email

        Glide.with(this)
            .load(avatarUrl)
            .into(ivAvatarDetail)
    }
}
