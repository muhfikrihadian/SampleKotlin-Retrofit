package com.muhfikrih.simplerestapi.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.models.CommentResponse
import kotlinx.android.synthetic.main.item_comment.view.*

class CommentAdapter(private val list: ArrayList<CommentResponse>) : RecyclerView.Adapter<CommentAdapter.CommentViewHolder>(){
    inner class CommentViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun bind(commentResponse: CommentResponse){
            with(itemView){
                val name = commentResponse.name
                val email = commentResponse.email
                val body = commentResponse.body

                tvName.text = name
                tvEmail.text = email
                tvBody.text = body
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment, parent, false)
        return CommentViewHolder(view)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}