package com.lucianoluzzi.login.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucianoluzzi.login.R
import org.koin.android.ext.android.get

class LoginActivity : AppCompatActivity() {
    private val loginFragmentFactory: LoginFragmentFactory = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = loginFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    companion object {
        fun getIntent(context: Context) = Intent(context, LoginActivity::class.java)
    }
}