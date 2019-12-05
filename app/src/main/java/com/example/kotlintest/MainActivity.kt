package com.example.kotlintest

import android.app.Service
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val LOG_TAG = "TEST"
    lateinit var myIntent: Intent
    var counter = 0

    val myServiceConnection = object: ServiceConnection {
        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(LOG_TAG, "onServiceDisconnected $name")
        }

        override fun onServiceConnected(name: ComponentName?, service: IBinder?)
        {
            Log.i(LOG_TAG, "onServiceConnected $name")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myIntent = Intent(this, MyService::class.java)
        var paramsExtra  = Bundle()

        start_service.setOnClickListener {
            paramsExtra.putInt("START COUNT", counter)
            myIntent.putExtra("EXTRA", paramsExtra)
            startService(myIntent)
            counter++
        }

        stop_service.setOnClickListener {
            stopService(myIntent)
        }

        bind_service.setOnClickListener {
            bindService(myIntent, myServiceConnection, Service.BIND_AUTO_CREATE)
        }

        unbind_service.setOnClickListener {
            unbindService(myServiceConnection)
        }
    }
}
