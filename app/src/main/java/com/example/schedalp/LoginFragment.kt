package com.example.schedalp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.schedalp.databinding.FragmentLoginBinding
import com.example.schedalp.model.Login
import com.example.schedalp.view.MainActivity
import com.example.schedalp.viewmodel.ScheduleViewModel
import com.example.schedalp.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Call
import retrofit2.Response

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [LoginFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class LoginFragment : Fragment() {
    private lateinit var viewModel: UserViewModel
    private lateinit var login: Button
    private lateinit var binding: FragmentLoginBinding
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginsubmit.setOnClickListener {
            val username = binding.username.editText!!.text.toString().trim()
            val password = binding.password.editText!!.text.toString().trim()

            viewModel = ViewModelProvider(this)[UserViewModel::class.java]
            viewModel.Login(username, password).enqueue(object : retrofit2.Callback<Login>{
                override fun onResponse(call: retrofit2.Call<Login>, response: retrofit2.Response<Login>) {
                    if (response.isSuccessful != null){
                        val intent = Intent(context, MainActivity::class.java)
                        Toast.makeText(context, response.body()?.id, Toast.LENGTH_SHORT).show()
                        intent.putExtra("login_id" , response.body()?.id?.toIntOrNull())
                        startActivity(intent)
                        Toast.makeText(context, "Login Success", Toast.LENGTH_SHORT).show()
                        activity?.finish()
                    }else{
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Login>, t: Throwable) {
                    Log.d("Login", "onFailure: ${t.message}")
                }
            })


        }
        // Inflate the layout for this fragment
        return binding.root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        login = view.findViewById(R.id.loginsubmit)
//        login.setOnClickListener {
//
//
//        }
//
//
//
//        super.onViewCreated(view, savedInstanceState)
//    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}