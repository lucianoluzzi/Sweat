package com.lucianoluzzi.analytics

import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase

class FirebaseProvider {
    fun getFirebaseAnalytics() = Firebase.analytics
}