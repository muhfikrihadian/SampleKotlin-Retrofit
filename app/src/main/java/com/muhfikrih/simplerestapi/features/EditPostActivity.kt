package com.muhfikrih.simplerestapi.features

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.muhfikrih.simplerestapi.R
import com.muhfikrih.simplerestapi.models.CreatePostResponse
import com.muhfikrih.simplerestapi.models.PostResponse
import com.muhfikrih.simplerestapi.retrofit.RetrofitClient
import kotlinx.android.synthetic.main.activity_add_post.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditPostActivity : AppCompatActivity() {
    private val list = ArrayList<PostResponse>()
    var id: Int = 1
    var idUser: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_post)
        setup()
    }

    private fun setup() {
        id = intent.getIntExtra("Id", 1)
        idUser = intent.getIntExtra("IdUser", 1)

        btnPost.setOnClickListener(View.OnClickListener {
            updatePost()
        })
        getDetailPost()
    }

    private fun getDetailPost() {
        RetrofitClient.instance.getPosts(idUser, id)
            .enqueue(object : Callback<ArrayList<PostResponse>> {
                override fun onResponse(
                    call: Call<ArrayList<PostResponse>>,
                    response: Response<ArrayList<PostResponse>>
                ) {
                    response.body()?.let { list.addAll(it) }
                    etTitle.setText(list.get(0).title)
                    etBody.setText(list.get(0).body)
                }

                override fun onFailure(call: Call<ArrayList<PostResponse>>, t: Throwable) {

                }
            })
    }

    private fun updatePost() {
        val title: String = etTitle.text.toString()
        val body: String = etBody.text.toString()

        RetrofitClient.instance.patchPost(id, id, idUser, title, body).enqueue(object :Callback<PostResponse>{
            override fun onResponse(call: Call<PostResponse>, response: Response<PostResponse>) {
                Toast.makeText(this@EditPostActivity, "Success !", Toast.LENGTH_SHORT).show()
                finish()
            }

            override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                Toast.makeText(this@EditPostActivity, "Failed !", Toast.LENGTH_SHORT).show()
            }

        })
    }
}