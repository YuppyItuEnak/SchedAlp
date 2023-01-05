package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast


import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.schedalp.R
import com.example.schedalp.adapter.ScheduleAdapter
import com.example.schedalp.databinding.ActivityMainBinding
import com.example.schedalp.retrofit.Listener
import com.example.schedalp.viewmodel.ScheduleViewModel
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ScheduleAdapter
    private  lateinit var viewModel: ScheduleViewModel
    lateinit var toggle : ActionBarDrawerToggle

    companion object{
        var loginschedId = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)


        viewModel = ViewModelProvider(this)[ScheduleViewModel::class.java]
        viewModel.getAllScheduleData()


        binding.createbutton.setOnClickListener {
            val intent = Intent(this, AddScheduleActivity::class.java)
            startActivity(intent)
        }
//        Toast.makeText(context, "Login ID: ${loginschedId}Id", Toast.LENGTH_SHORT).show()

        viewModel.dataschedule.observe(this, { response ->
            val layoutmanager = LinearLayoutManager(this)
            binding.mainrv.layoutManager = layoutmanager
            adapter = ScheduleAdapter(response.data as ArrayList)
            binding.mainrv.adapter = adapter
        })
    }

//    private fun replaceFragment1(homefragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        fragmentTransaction.replace(R.id.frameLayout, homefragment)
//        fragmentTransaction.commit()
//    }

//    private fun replacefragmentnav(fragment: Fragment){
//        val fragmentManager = supportFragmentManager.beginTransaction()
//
//        fragmentManager.replace(R.id.frameLayoutHome, fragment)
//
//    }
//    private fun replacefragmentLogin(loginframent: Fragment, title: String){
////        val fragmentManager = supportFragmentManager
////        val fragmentTransaction = fragmentManager.beginTransaction()
////        fragmentTransaction.replace(R.id.frameLayout, loginframent)
////
////        fragmentTransaction.commit()
////        drawerLayout.closeDrawers()
////        setTitle(title)
////    }
////    private fun replacefragmentProfile(profilefragment: Fragment, title: String){
////        val fragmentManager = supportFragmentManager
////        val fragmentTransaction = fragmentManager.beginTransaction()
////
////        fragmentTransaction.replace(R.id.FrameLayout, profilefragment)
////
////        fragmentTransaction.commit()
////        drawerLayout.closeDrawers()
////        setTitle(title)
////    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}