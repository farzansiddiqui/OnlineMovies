package com.siddiqui.onlinemovies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.siddiqui.onlinemovies.databinding.MovieLayoutBinding

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.MyViewModel>() {
    private var movieList = ArrayList<Result>()

    fun setMovieList(movieList : List<Result>) {
        this.movieList = movieList as ArrayList<Result>
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(MovieLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
            Glide.with(holder.itemView).load("https://image.tmdb.org/t/p/w500"+movieList[position].poster_path)
                .into(holder.binding.movieImage)
        holder.binding.movieName.text = movieList[position].title
    }
    inner class MyViewModel(val binding:MovieLayoutBinding) : RecyclerView.ViewHolder(binding.root){}
}