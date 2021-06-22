package com.example.services

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.widget.Button
import android.widget.Toast

class BoundedServiceActivity : AppCompatActivity() {

    private lateinit var boundedService: BoundedService
    private var bounded: Boolean = false

    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val binder = service as BoundedService.LocalBinder
            boundedService = binder.getService()
            bounded = true
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            bounded = false
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bounded_service)

        findViewById<Button>(R.id.boundedServiceButton)?.setOnClickListener {
            bindService(
                Intent(this, BoundedService::class.java),
                connection,
                Context.BIND_AUTO_CREATE
            )
        }

        findViewById<Button>(R.id.callServiceMethodButton)?.setOnClickListener {
            if (bounded) {
                val num: Int = boundedService.randomNumber
                Toast.makeText(this, "number: $num", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onStop() {
        super.onStop()
        unbindService(connection)
        bounded = false
    }
}