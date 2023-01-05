package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.schedalp.R
import com.example.schedalp.databinding.ActivityMainBinding
import com.example.schedalp.databinding.ActivitySignUpBinding
import com.example.schedalp.viewmodel.ScheduleViewModel
import com.example.schedalp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var user: UserViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(binding.root)
        register()

        binding.login.setOnClickListener{
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun register(){
        binding.submitreg.setOnClickListener {
            user = ViewModelProvider(this)[UserViewModel::class.java]
            var username = binding.inputusername.editText!!.text.toString()
            var email = binding.inputemail.editText!!.text.toString()
            var pass = binding.inputpass.editText!!.text.toString()

            user.userstate = user.userstate.copy(
                username = username,
                email = email,
                password = pass,
            )
            user.Register()
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}