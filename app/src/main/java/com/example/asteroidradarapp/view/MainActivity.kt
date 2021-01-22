package com.example.asteroidradarapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.asteroidradarapp.R

class MainActivity : AppCompatActivity() {

    /**
     * MainActivity is only responsible for setting the content view that contains the
     * Navigation Host.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}