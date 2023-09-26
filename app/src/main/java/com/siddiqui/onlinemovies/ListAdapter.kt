package com.siddiqui.onlinemovies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ListAdapter(val arrayList: ArrayList<ProfileModel>): RecyclerView.Adapter<ListAdapter.MyCustomViewModel>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCustomViewModel {
              val view = LayoutInflater.from(parent.context).inflate(R.layout.profile_layout,parent,false)
              return MyCustomViewModel(view)
        }

        override fun getItemCount(): Int {
              return  arrayList.size
        }

        override fun onBindViewHolder(holder: MyCustomViewModel, position: Int) {
                holder.bind(arrayList[position])

        }
        inner class MyCustomViewModel(itemView: View): RecyclerView.ViewHolder(itemView) {
                var textView = itemView.findViewById<TextView>(R.id.nameProfile)
                var emailTextView = itemView.findViewById<TextView>(R.id.emailId)
                var imageView  = itemView.findViewById<ImageView>(R.id.imageView)
                fun bind(profileModel: ProfileModel) {
                        textView.text = profileModel.name
                        emailTextView.text = profileModel.email
                        Picasso.get().load(profileModel.imageUrl).into(imageView)
                }
        }
}
