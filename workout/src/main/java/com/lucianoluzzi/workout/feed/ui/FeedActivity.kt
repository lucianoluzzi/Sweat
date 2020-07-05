package com.lucianoluzzi.workout.feed.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.lucianoluzzi.domain.Profile
import com.lucianoluzzi.workout.R
import com.lucianoluzzi.workout.WorkoutFragmentFactory
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class FeedActivity : AppCompatActivity() {
    private val feedFragmentFactory by lazy<WorkoutFragmentFactory> {
        get {
            parametersOf(profile)
        }
    }

    private val profile by lazy {
        intent.getSerializableExtra("PROFILE") as Profile
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        supportFragmentManager.fragmentFactory = feedFragmentFactory
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
    }
}