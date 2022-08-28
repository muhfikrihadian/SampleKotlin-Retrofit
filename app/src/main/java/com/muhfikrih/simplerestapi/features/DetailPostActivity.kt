package com.muhfikrih.simplerestapi.features

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.adapter.CommentAdapter
import com.muhfikrih.simplerestapi.models.CommentResponse
import com.muhfikrih.simplerestapi.models.PostResponse
import com.muhfikrih.simplerestapi.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_detail_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPostActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()
    private val listComment = ArrayList<CommentResponse>()
    var id: Int = 1
    var idUser: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_post)
        setup()
    }

    private fun setup() {
        id = intent.getIntExtra("Id", 1)
        idUser = intent.getIntExtra("IdUser", 1)

        rvComments.setHasFixedSize(true)
        rvComments.layoutManager = LinearLayoutManager(this)
        getDetailPost()
        getComments()

        btnEdit.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, EditPostActivity::class.java)
            intent.putExtra("Id", id)
            intent.putExtra("IdUser", idUser)
            startActivity(intent)
        })
        btnDelete.setOnClickListener(View.OnClickListener {
            deletePost()
        })
    }

    private fun deletePost() {
        RetrofitClient.instance.deletePost(id).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Toast.makeText(this@DetailPostActivity, "Success !", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(this@DetailPostActivity, "Failed, " + t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun getDetailPost() {
        RetrofitClient.instance.getPosts(idUser, id)
            .enqueue(object : Callback<ArrayList<PostResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
                ) {
                    response.body()?.let { list.addAll(it) }
                    tvTitle.text = list.get(0).title
                    tvBody.text = list.get(0).body
                }

                override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

                }
            })
    }

    private fun getComments() {
        RetrofitClient.instance.getComments(id)
            .enqueue(object : Callback<ArrayList<CommentResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<CommentResponse>>,
                    response: Response<ArrayList<CommentResponse>>
                ) {
                    response.body()?.let { listComment.addAll(it) }
                    val adapter = CommentAdapter(listComment)
                    rvComments.adapter = adapter
                }

                override fun onFailure(call: Call<ArrayList<CommentResponse>>, t: Throwable) {

                }
            })
    }
}