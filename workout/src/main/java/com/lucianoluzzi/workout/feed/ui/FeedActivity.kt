package com.lucianoluzzi.workout.feed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucianoluzzi.workout.R
import org.koin.android.ext.android.get

class FeedActivity : AppCompatActivity() {
    private val feedFragmentFactory: WorkoutFragmentFactory = get()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = feedFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }
}