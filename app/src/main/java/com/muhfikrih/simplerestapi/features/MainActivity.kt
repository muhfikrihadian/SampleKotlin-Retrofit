package com.muhfikrih.simplerestapi.features

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhfikrih.simplerestapi.adapter.PostAdapter
import com.muhfikrih.simplerestapi.models.PostResponse
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup() {
        btnPost.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddPostActivity::class.java)
            startActivity(intent)
        })
        rvPosts.setHasFixedSize(true)
        rvPosts.layoutManager = LinearLayoutManager(this)
        getPost()
    }

    private fun getPost() {
        RetrofitClient.instance.getPosts().enqueue(object : Callback<ArrayList<PostResponse>> {
            override fun onResponse(
                call: Call<ArrayList<PostResponse>>,
                response: Response<ArrayList<PostResponse>>
            ) {
                response.body()?.let { list.addAll(it) }
                val adapter = PostAdapter(list)
                rvPosts.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

            }
        })
    }
}