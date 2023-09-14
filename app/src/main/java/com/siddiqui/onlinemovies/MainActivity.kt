package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonObject
import com.siddiqui.onlinemovies.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        prepareRecyclerView()
        viewModel = ViewModelProvider(this)[MovieViewModel::class.java]
        viewModel.getPopularMovies()
        viewModel.observeMovieLiveData().observe(this) { movieList ->
            movieAdapter.setMovieList(movieList)
        }
        binding.click.setOnClickListener {
            val intent = Intent(this, MainActivityB::class.java)
            startActivity(intent)

        }
        binding.button.setOnClickListener {
            startActivity(Intent(this, MainActivityD::class.java))
        }
        val queue = Volley.newRequestQueue(this)
        var url = "https://reqres.in/api/users";

        val jsonOObject = JSONObject()
        jsonOObject.put("name","morpheus")
        jsonOObject.put("job","leader")


        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,null, {


        }, {

        })

    }
    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }
}