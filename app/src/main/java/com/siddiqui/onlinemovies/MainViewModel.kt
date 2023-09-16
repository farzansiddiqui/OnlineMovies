package com.siddiqui.onlinemovies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel:ViewModel() {
    var lst = MutableLiveData<ArrayList<Blog>>()
    var newList = arrayListOf<Blog>()

    fun addBlog(blog: Blog){
        newList.add(blog)
        lst.value = newList
    }
    fun remove(blog: Blog){
        newList.remove(blog)
        lst.value = newList
    }

}