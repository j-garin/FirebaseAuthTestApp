package com.jgarin.authtest.firebaseauthtestapp

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException

class ProcessLoginActivity: AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		val email = PreferenceManager.getDefaultSharedPreferences(this)
			.getString(EMAIL_PREFS_KEY, null) ?: throw IllegalStateException("email not saved")

		val emailLink = intent.data?.toString() ?: throw IllegalArgumentException("failed to retrieve email link from intent")

		val auth = FirebaseAuth.getInstance()

		auth.signInWithEmailLink(email, emailLink)
			.addOnSuccessListener { tvMessage.text = "Logged in"; progressBar.visibility = View.INVISIBLE }
			.addOnFailureListener { tvMessage.text = "Error: ${it.message}"; progressBar.visibility = View.INVISIBLE }

	}

}