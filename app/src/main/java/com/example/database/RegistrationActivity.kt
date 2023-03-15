package com.example.database

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import database.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    companion object {
        val TAG = "RegistrationActivity"
    }

    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonRegistrationSubmit.setOnClickListener {

        }

        // retrieve any information from the intent
        val username = intent.getStringExtra(LoginActivity.EXTRA_USERNAME) ?: ""
        // VERY INSECURE, **NEVER** PASS PASSWORDS IN PLAIN TEXT
        val password = intent.getStringExtra(LoginActivity.EXTRA_PASSWORD) ?: ""

        // prefill the username and password & username
        binding.editTextTextUsername.setText(username)
        binding.editTextTextPassword.setText(password)


        // register an account and send bac the username && password
        // to the login activity to prefill those fields
        binding.buttonRegistrationSubmit.setOnClickListener {
            if (!formIsCorrectlyFilled()) {
                Toast.makeText(this, "Fill the form correctly!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            val extraUsername = binding.editTextTextUsername.text.toString()
            val extraPassword = binding.editTextTextPassword.text.toString()
            // apply lambda will call the functions inside it on the object that apply is called on
            val resultIntent = Intent(this, RegistrationActivity::class.java).apply {
                putExtra(LoginActivity.EXTRA_USERNAME, extraUsername)
                putExtra(LoginActivity.EXTRA_PASSWORD, extraPassword)
            }
            Log.d(TAG,    "\n"
                    + "submit: name=${binding.editTextTextName.text}\n"
                    + "      * username=${binding.editTextTextUsername.text}\n"
                    + "      * password=${binding.editTextTextPassword.text}\n"
                    + "      * conf-password=${binding.editTextTextPasswordConfirm.text}\n"
                    + "      * email=${binding.editTextTextEmailAddress.text}\n")
            setResult(Activity.RESULT_OK, resultIntent)
            finish()

        }

    }

    private fun formIsCorrectlyFilled(): Boolean {
        return RegistrationUtil.validatePassword(binding.editTextTextPassword.text.toString(),
                                                binding.editTextTextPasswordConfirm.text.toString())
                // && RegistrationUtil.validateName(binding.editTextTextName.text.toString())
                // && RegistrationUtil.validateEmail(binding.editTextTextEmailAddress.text.toString())
                // && RegistrationUtil.validateUser(binding.editTextTex)
    }
}