package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.schedalp.LoginFragment
import com.example.schedalp.R
import com.example.schedalp.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(binding.root)
        replacefragment(LoginFragment())

//        binding.register.setOnClickListener{
//            val intent = Intent(this, SignUpActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
    }


    private fun replacefragment(LoginFragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()

        fragmentTransaction.replace(R.id.FrameLayout, LoginFragment)
        fragmentTransaction.commit()
    }
}