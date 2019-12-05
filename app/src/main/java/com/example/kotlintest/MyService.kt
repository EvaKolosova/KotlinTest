package com.example.kotlintest

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.util.Log

class MyService : Service() {

    override fun onCreate() {
        super.onCreate()
        Log.i("TEST", "Service created!")
    }

    override fun onBind(intent: Intent): Binder? {
        Log.i("TEST", "Service binded!")
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
        Log.i("TEST", "Service rebinded!")
    }

    override fun onUnbind(intent: Intent): Boolean {
        Log.i("TEST", "Service unbinded!")
        return super.onUnbind(intent)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var bundle = intent?.getBundleExtra("EXTRA")
        var count = bundle?.getInt("START COUNT", -1) ?: -1
        Log.i("TEST", "Start count - value is: $count")

        return Service.START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("TEST", "Service destroyed.")
    }
}