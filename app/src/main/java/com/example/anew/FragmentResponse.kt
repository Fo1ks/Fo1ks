package com.example.anew

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.anew.databinding.FragmentResponseBinding
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class FragmentResponse : Fragment() {
    lateinit var binding: FragmentResponseBinding

    val scope = CoroutineScope(Dispatchers.Main)

    val api: JsonApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(JsonApi::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentResponseBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val userId = it.getString("userId", "").toInt()
            val title = it.getString("title", "")
            val body = it.getString("body", "")

            scope.launch {
                val post = withContext(Dispatchers.IO) { api.createPost(Post(userId, title, body)) }
                binding.progress.visibility = View.GONE
                binding.Response.text = "id = ${post.id}\nuserId = ${post.userId}\ntitle = ${post.title}\nbody = ${post.body}"
            }
        }
    }
}