package com.jgarin.authtest.firebaseauthtestapp

import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.ActionCodeSettings
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

const val EMAIL_PREFS_KEY = "email"

class MainActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		login.addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(input: Editable?) {
				btnLogin.isEnabled = Patterns.EMAIL_ADDRESS.matcher(input).matches()
			}

			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

			override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
		})

		btnLogin.setOnClickListener { _ ->
			val settings = ActionCodeSettings.newBuilder()
				.setUrl("https://firebaseauthtest.jgarin.com/verify.email")
				.setAndroidPackageName(packageName, true, "1")
				.setHandleCodeInApp(true)
				.build()
			val email = login.text.toString()
			FirebaseAuth.getInstance().sendSignInLinkToEmail(email, settings)
				.addOnSuccessListener {
					PreferenceManager.getDefaultSharedPreferences(this)
						.edit().putString(EMAIL_PREFS_KEY, email).apply()
					Toast.makeText(this, "Email sent", Toast.LENGTH_SHORT).show()
				}
				.addOnFailureListener { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
		}

	}
}
