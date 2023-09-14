package com.siddiqui.onlinemovies

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class MainActivityB : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_b)

        findViewById<Button>(R.id.clickBtnB).setOnClickListener {
            startActivity(Intent(this, MainActivityC::class.java))
        }

        findViewById<Button>(R.id.btn_AgainClick).setOnClickListener {
            startActivity(Intent(this, MainActivityB::class.java))
        }

    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Toast.makeText(this, "on New Intent", Toast.LENGTH_SHORT).show();
    }
}