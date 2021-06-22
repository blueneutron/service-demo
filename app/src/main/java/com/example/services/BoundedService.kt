package com.example.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import java.util.*

class BoundedService : Service() {

    private val binder: Binder = LocalBinder()

    private val mGenerator = Random()

    val randomNumber: Int
        get() = mGenerator.nextInt(100)

    override fun onBind(intent: Intent): IBinder {
        return binder;
    }

    inner class LocalBinder : Binder() {
        fun getService(): BoundedService = this@BoundedService
    }
}