package com.siddiqui.onlinemovies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserViewModel:ViewModel() {

    private var mutableLiveData = MutableLiveData<UserData>()

    val myData:LiveData<UserData>
        get() = mutableLiveData

    fun valueSet(userData: UserData){
        mutableLiveData.value = userData
    }

    fun onMyDataChanged(listener:(MutableLiveData<UserData>)-> Unit){
        listener(mutableLiveData)
    }

}