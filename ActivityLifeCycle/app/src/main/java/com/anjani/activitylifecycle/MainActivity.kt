package com.anjani.activitylifecycle

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import timber.log.Timber

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Timber.i("onCreate called")
    }

    override fun onStart()
    {
        super.onStart()
        Timber.i("onStart called  ")

    }

    override fun onResume() {
        super.onResume()
        Timber.i("onResume called ")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause called ")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy called ")

    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart called ")
    }
}
