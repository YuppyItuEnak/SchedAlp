package com.example.schedalp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem


import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.schedalp.HomeFragment
import com.example.schedalp.LoginFragment
import com.example.schedalp.ProfileFragment
import com.example.schedalp.R
import com.example.schedalp.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
//    private lateinit var adapter: ScheduleAdapter
//    private  lateinit var viewModel: ScheduleViewModel
    lateinit var toggle : ActionBarDrawerToggle
    lateinit var  drawerLayout : DrawerLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


       drawerLayout = findViewById(R.id.navbar)
        val navview : NavigationView = findViewById(R.id.view)



        toggle = ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        replacefragmentnav(HomeFragment())
        binding.view.setNavigationItemSelectedListener {

            when(it.itemId){
                R.id.nav_home -> replacefragmentnav(HomeFragment())
                R.id.nav_profile -> replacefragmentnav(ProfileFragment())
                R.id.nav_login -> replacefragmentnav(LoginFragment())
            }
            true
        }
    }

//    private fun replaceFragment1(homefragment: Fragment){
//        val fragmentManager = supportFragmentManager
//        val fragmentTransaction = fragmentManager.beginTransaction()
//
//        fragmentTransaction.replace(R.id.frameLayout, homefragment)
//        fragmentTransaction.commit()
//    }

    private fun replacefragmentnav(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayoutHome, fragment)
        fragmentTransaction.commit()
    }
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