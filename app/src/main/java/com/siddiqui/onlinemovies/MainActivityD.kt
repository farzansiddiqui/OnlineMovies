package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivityD : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_d)
        Toast.makeText(this,"onCreate",Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.clickBtnD).setOnClickListener {
            startActivity(Intent(this,MainActivityD::class.java))
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this,"onNewIntent",Toast.LENGTH_SHORT).show()
    }
}