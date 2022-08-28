package com.muhfikrih.simplerestapi.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.features.AddPostActivity
import com.muhfikrih.simplerestapi.features.DetailPostActivity
import com.muhfikrih.simplerestapi.features.MainActivity
import com.muhfikrih.simplerestapi.models.PostResponse
import kotlinx.android.synthetic.main.item_post.view.*


class PostAdapter(private val list: ArrayList<PostResponse>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>(){
    inner class PostViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(postResponse: PostResponse){
            with(itemView){
                val title = postResponse.title
                val body = postResponse.body

                tvTitle.text = title
                tvBody.text = body

                itemView.setOnClickListener(View.OnClickListener {
                    val intent = Intent(context, DetailPostActivity::class.java)
                    intent.putExtra("Id", postResponse.id)
                    intent.putExtra("IdUser", postResponse.userId)
                    context.startActivity(intent)
                })
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        return PostViewHolder(view)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}