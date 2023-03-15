package com.example.database

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.backendless.Backendless
import com.backendless.BackendlessUser
import com.backendless.async.callback.AsyncCallback
import com.backendless.exceptions.BackendlessFault
import database.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    companion object {
        // the values to send in intents are called Extras
        // and have the EXTRA_BLAH format for naming the key
        const val EXTRA_USERNAME = "username"
        const val EXTRA_PASSWORD = "very_unsafe"
        const val TAG = "LoginActivity"
    }

    val startRegistrationForResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
    { result: ActivityResult ->
        if (result.resultCode == Activity.RESULT_OK) {
            val intent = result.data
            // Handle the Intent
            binding.editTextLoginUserName.setText(intent?.getStringExtra(EXTRA_USERNAME))
            binding.editTextLoginPassword.setText(intent?.getStringExtra(EXTRA_PASSWORD))
        }
    }

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Backendless
        Backendless.initApp(this, Constants.APPLICATION_ID, Constants.ANDROID_API_KEY)

        binding.textViewLoginSignUp.setOnClickListener {
            // 1. create an Intent object with the current activity
            // and the destination activity's class
            val registrationIntent = Intent(this,
                                            RegistrationActivity::class.java)

            // 2. optionally add information to send with the intent
            // use key-value pairs just like bundles
            val extraUsername = binding.editTextLoginUserName.text.toString()
            registrationIntent.putExtra(EXTRA_USERNAME, extraUsername)
            val extraPassword = binding.editTextLoginPassword.text.toString()
            registrationIntent.putExtra(EXTRA_PASSWORD, extraPassword)

            // 3. launch the new activity using the intent
            // startActivity(registrationIntent)
            startRegistrationForResult.launch(registrationIntent)

            Log.d(TAG, "\nonCreate: password=${extraPassword}" +
                            "\n          username=${extraUsername}")
        }

        binding.buttonLoginConfirmLogin.setOnClickListener {
            Backendless.UserService.login(
                binding.editTextLoginUserName.text.toString(),
                binding.editTextLoginPassword.text.toString(),
                object : AsyncCallback<BackendlessUser> {
                    override fun handleResponse(response: BackendlessUser?) {
                        Log.d(TAG, "handleResponse: $response")
                    }

                    override fun handleFault(fault: BackendlessFault?) {
                        Log.d(TAG, "handleFault: $fault")
                    }

                }
            )

        }
    }
}