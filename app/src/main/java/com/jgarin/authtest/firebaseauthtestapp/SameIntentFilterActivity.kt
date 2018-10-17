package com.jgarin.authtest.firebaseauthtestapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SameIntentFilterActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_same_intent_filter)

		Log.d("intercept", "Login intent intercepted in ${this.javaClass.simpleName}")
	}
}