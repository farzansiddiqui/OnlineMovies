package com.siddiqui.onlinemovies

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonObjectRequest
import com.siddiqui.onlinemovies.databinding.ActivityMainBBinding

class MainActivityB : AppCompatActivity() {

    val arrayList = ArrayList<ProfileModel>()
    lateinit var adapter: ListAdapter
    lateinit var binding:ActivityMainBBinding
    private lateinit var queue: RequestQueue
    val TAG = "TAG"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        volleyLoad()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val progressBar = ProgressBar(this, null,android.R.attr.progressBarStyleSmall)
        progressBar.visibility = View.VISIBLE

      /*  findViewById<Button>(R.id.clickBtnB).setOnClickListener {
            startActivity(Intent(this, MainActivityC::class.java))
        }

        findViewById<Button>(R.id.btn_AgainClick).setOnClickListener {
            startActivity(Intent(this, MainActivityB::class.java))
        }*/

    }

  /*  override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "on New Intent", Toast.LENGTH_SHORT).show();
    }*/

    private fun volleyLoad(){
        val url = "https://reqres.in/api/users"
        queue = MySingleton.getInstance(this.applicationContext).requestQueue

        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET,url,null, {
                    val jsonArray = it.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val fName = jsonObject.getString("first_name")
                        val lName = jsonObject.getString("last_name")
                        val fullName = "$fName $lName"
                        val email = jsonObject.getString("email")
                        val imgUrl = jsonObject.getString("avatar")
                        arrayList.add(ProfileModel(fullName,email, imgUrl))
                    }
            adapter = ListAdapter(arrayList)
            binding.recyclerView.adapter = adapter

        }, {
            it.printStackTrace()
        })

        jsonObjectRequest.tag = TAG
        MySingleton.getInstance(this).addRequestQueue(jsonObjectRequest)
    }

    override fun onStop() {
        super.onStop()
        queue.cancelAll(TAG)
    }
}