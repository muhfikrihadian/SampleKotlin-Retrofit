package com.muhfikrih.simplerestapi.features

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.models.CreatePostResponse
import com.muhfikrih.simplerestapi.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddPostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_post)

        btnPost.setOnClickListener(View.OnClickListener {
            createPost()
        })
    }

    private fun createPost() {
        val userId:Int = 14
        val title:String = etTitle.text.toString()
        val body:String = etBody.text.toString()

        RetrofitClient.instance.createPost(userId, title, body).enqueue(object : Callback<CreatePostResponse>{
            override fun onResponse(call: Call<CreatePostResponse>, response: Response<CreatePostResponse>) {
                Toast.makeText(this@AddPostActivity, "Success !", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<CreatePostResponse>, t: Throwable) {
                Toast.makeText(this@AddPostActivity, "Failed !", Toast.LENGTH_SHORT).show()
            }
        })
    }
}