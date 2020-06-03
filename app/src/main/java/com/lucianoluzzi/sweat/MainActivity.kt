package com.lucianoluzzi.sweat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lucianoluzzi.login.ui.LoginActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        val loginActivity = LoginActivity.getIntent(this)
        startActivity(loginActivity)
    }
}