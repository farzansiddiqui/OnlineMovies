package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivityC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_c)
        Toast.makeText(this,"onCreate", Toast.LENGTH_SHORT).show()
        findViewById<Button>(R.id.clickBtnC).setOnClickListener {
            startActivity(Intent(this, MainActivityC::class.java))
        }

        findViewById<Button>(R.id.openActivityB).setOnClickListener {
            startActivity(Intent(this, MainActivityB::class.java))
        }

        findViewById<Button>(R.id.openActivityD).setOnClickListener {
            startActivity(Intent(this, MainActivityD::class.java))
        }


    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "on new intent",Toast.LENGTH_SHORT).show()
    }
}