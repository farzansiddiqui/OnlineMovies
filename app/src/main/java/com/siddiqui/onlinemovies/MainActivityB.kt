package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.JsonArray
import com.squareup.picasso.Picasso

class MainActivityB : AppCompatActivity() {

    val arrayList = ArrayList<ProfileModel>()
    lateinit var adapter: ListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_b)




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
        val queue = Volley.newRequestQueue(this)

        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST,url,null, {
                    val jsonArray = it.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        val jsonObject = jsonArray.getJSONObject(i)
                        val fName = jsonObject.getString("first_name")
                        var lName = jsonObject.getString("last_name")
                        val fullName = "$fName $lName"
                        var email = jsonObject.getString("email")
                        val imgUrl = jsonObject.getString("avatar")
                        arrayList.add(ProfileModel(fullName,email,)

                    }
        }, {

        })
    }
}