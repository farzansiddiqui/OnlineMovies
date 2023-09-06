package com.siddiqui.onlinemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.siddiqui.onlinemovies.databinding.ActivityMainBinding

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
    }
    private fun prepareRecyclerView() {
        movieAdapter = MovieAdapter()
        binding.rvMovies.apply {
            layoutManager = GridLayoutManager(applicationContext,2)
            adapter = movieAdapter
        }
    }
}