package com.siddiqui.onlinemovies

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NoteRecyclerAdapter(val mainViewModel: MainViewModel,val arrayList: ArrayList<Blog>,context: Context):RecyclerView.Adapter<NoteRecyclerAdapter.MyCustomViewModel>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteRecyclerAdapter.MyCustomViewModel {
        var root = LayoutInflater.from(parent.context).inflate(R.layout.recycler_view,parent,false)
        return MyCustomViewModel(root)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: MyCustomViewModel, position: Int) {
      holder.bind(arrayList[position])
    }
    inner class MyCustomViewModel(private val binding:View): RecyclerView.ViewHolder(binding){
        val title = binding.findViewById<TextView>(R.id.title)
        val imgBtn = binding.findViewById<ImageButton>(R.id.imgBtn)
        fun bind(blog: Blog) {
            title.text = blog.title
            imgBtn.setOnClickListener {
                mainViewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }
}