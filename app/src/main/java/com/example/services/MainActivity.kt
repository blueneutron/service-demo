package com.example.services

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.buttonUnboundService)?.setOnClickListener {
            startService(Intent(this, UnboundedService::class.java))
        }

        findViewById<Button>(R.id.buttonBoundService)?.setOnClickListener {
            startActivity(Intent(this, BoundedServiceActivity::class.java))
        }

        findViewById<Button>(R.id.buttonForegroundService)?.setOnClickListener {
            startService(Intent(this, ForegroundService::class.java))
        }


        findViewById<Button>(R.id.buttonMessageService)?.setOnClickListener {
            startActivity(Intent(this, MessageServiceActivity::class.java))
        }
    }

}