package com.example.retrofitglide_2566.db

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofitglide_2566.DetailActivity
import com.example.retrofitglide_2566.R

class UserAdapter : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private val userList = mutableListOf<User>()

    fun setData(data: List<User>) {
        userList.clear()
        userList.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]

        Glide.with(holder.itemView.context)
            .load(user.avatar)
            .circleCrop()
            .into(holder.imgAvatar)

        holder.tvName.text = "${user.first_name} ${user.last_name}"
        holder.tvEmail.text = user.email

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)
            intent.putExtra("USER_NAME", "${user.first_name} ${user.last_name}")
            intent.putExtra("USER_EMAIL", user.email)
            intent.putExtra("USER_AVATAR_URL", user.avatar)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = userList.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
        val tvName: TextView = itemView.findViewById(R.id.tv_name)
        val tvEmail: TextView = itemView.findViewById(R.id.tv_email)
    }
}