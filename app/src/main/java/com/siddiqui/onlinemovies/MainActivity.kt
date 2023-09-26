package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.siddiqui.onlinemovies.databinding.ActivityMainBinding
import kotlinx.coroutines.yield
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var binding:ActivityMainBinding
    private lateinit var viewModel: MovieViewModel
    private lateinit var movieAdapter : MovieAdapter
    var queue: RequestQueue? = null
    private lateinit var mainViewModel: MainViewModel
    private var viewManager = LinearLayoutManager(this)

    val TAG = "MyTag"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = MainViewModelFactory()
        mainViewModel = ViewModelProvider(this,factory)[MainViewModel::class.java]
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

        val url = "https://reqres.in/api/users";
      //  val queue = Volley.newRequestQueue(this)
        queue = Volley.newRequestQueue(this)
        val jsonOObject = JSONObject()
        jsonOObject.put("name","morpheus")
        jsonOObject.put("job","leader")

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,jsonOObject, {
           val name = it.getString("name");
            val job = it.getString("job")

            Log.d("TAG", "fetch from server: $name $job")

        }, {
        it.printStackTrace()
        })
        jsonObjectRequest.tag = TAG
        queue?.add(jsonObjectRequest)

    volleyGet()

        binding.addBtn.setOnClickListener {
            addData()
        }

        initialiseAdapter()

    }
    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }

    private fun initialiseAdapter(){
        binding.recyclerViewList.layoutManager = viewManager
        observeData()
    }
    suspend fun task1(){
        Log.d(TAG, "task1: ")
           yield()
    }

    fun observeData(){
        mainViewModel.lst.observe(this, Observer{
            binding.recyclerViewList.adapter = NoteRecyclerAdapter(mainViewModel, it, this)
        })
    }


    private fun volleyGet() {

        val url = "https://reqres.in/api/users?page=2"
        val requestQueue = Volley.newRequestQueue(this)
        val listJsonResponse = mutableListOf<String>()

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url,null, {
            val jsonArray = it.getJSONArray("data")

            for (i in 0 until jsonArray.length()){
                val jsonObject = jsonArray.getJSONObject(i)
                val email = jsonObject.getString("email")
                listJsonResponse.add(email)
            }

            Log.d("TAG", "volleyGet: $listJsonResponse")

        }, {

            it.printStackTrace()
        })
        requestQueue.add(jsonObjectRequest)
    }

    override fun onStop() {
        super.onStop()
        queue?.cancelAll(TAG)
    }

    fun addData(){
        val title = binding.editText.text.toString()
        if (title.isBlank()){
            Toast.makeText(this@MainActivity, "Enter Value!", Toast.LENGTH_SHORT).show()
        }else{
            var blog = Blog(title)
            mainViewModel.addBlog(blog)
            binding.editText.text.clear()
            binding.recyclerViewList.adapter?.notifyDataSetChanged()
        }
    }
}