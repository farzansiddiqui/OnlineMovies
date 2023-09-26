package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivityD : AppCompatActivity() {

    lateinit var viewModel: UserViewModel
   lateinit var userData:UserData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_d)
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()

        viewModel = ViewModelProvider(this)[UserViewModel::class.java]

        viewModel.myData.observe(this, Observer {
            // Update the Ui with the Data
        })

        userData = UserData("johtn", "paass")

        viewModel.valueSet(userData)

        findViewById<Button>(R.id.clickBtnD).setOnClickListener {
            startActivity(Intent(this,MainActivityD::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this,"onNewIntent",Toast.LENGTH_SHORT).show()
    }
}