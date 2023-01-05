package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider

import com.example.schedalp.R
import com.example.schedalp.databinding.ActivityLoginBinding
import com.example.schedalp.model.Login
import com.example.schedalp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(binding.root)

Login()



        binding.register.setOnClickListener{
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun Login(){
        binding.submitlog.setOnClickListener {
            val username = binding.usernamelog.editText!!.text.toString()
            val password = binding.passwordlog.editText!!.text.toString()

            var isCompleted: Boolean = true

            // username
            if (username.isEmpty()) {
                binding.usernamelog.error = "Please fill your username"
                isCompleted = false
            } else {
                binding.usernamelog.error = ""
            }

            // password
            if (password.isEmpty()) {
                binding.passwordlog.error = "Please fill your password"
                isCompleted = false
            } else {
                binding.passwordlog.error = ""
            }

            if (isCompleted){
                viewModel = ViewModelProvider(this@LoginActivity).get(UserViewModel::class.java)
                viewModel.login(username, password).enqueue(object : retrofit2.Callback<Login> {
                    override fun onResponse(call: Call<Login>, response: Response<Login>) {
                        if (response.isSuccessful ) {
                            val myIntent = Intent(this@LoginActivity, MainActivity::class.java)
                            val login = response.body()
                            if (login != null) {
                                Toast.makeText(this@LoginActivity, login.login_id, Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this@LoginActivity, "Error: Login object is null", Toast.LENGTH_SHORT).show()
                            }


                            intent.putExtra("login_id", login?.login_id?.toInt())
                            startActivity(myIntent)
                            Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                            finish()
                        } else {
                            Toast.makeText(this@LoginActivity, "Login Failed", Toast.LENGTH_SHORT).show()
                        }



                    }
//                    if(response.body()?.id != null){
////                                Toast.makeText(this@LoginActivity, "ada", Toast.LENGTH_SHORT).show()
////                            }else {
////                                Toast.makeText(this@LoginActivity, "Tidak Ada ", Toast.LENGTH_SHORT).show()
////                            }

                    override fun onFailure(call: retrofit2.Call<Login>, t: Throwable) {
                        Log.d("Login", "onFailure: ${t.message}")
                    }
                })

            }

        }
    }


}