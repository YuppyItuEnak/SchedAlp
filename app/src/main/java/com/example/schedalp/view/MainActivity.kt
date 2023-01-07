package com.example.schedalp.view

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast


import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
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
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ScheduleAdapter
    private  lateinit var viewModel: ScheduleViewModel
    lateinit var toggle : ActionBarDrawerToggle






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        viewModel = ViewModelProvider(this)[ScheduleViewModel::class.java]
        viewModel.getAllScheduleData()
        viewModel.dataschedule.observe(this, { response ->
            val layoutmanager = LinearLayoutManager(this)
            binding.mainrv.layoutManager = layoutmanager
            adapter = ScheduleAdapter(response.data as ArrayList)
            binding.mainrv.adapter = adapter
        })


        viewModel.state = viewModel.state.copy(
            id = intent.getIntExtra("del_id", 0).toString()
        )
        if(viewModel.state.id != "0"){
            viewModel.DeleteSchedule()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        binding.profil.setOnClickListener {
            val profil = Intent(this@MainActivity, ProfileActivity::class.java)
            startActivity(profil)
        }




        binding.createbutton.setOnClickListener {
            val intent = Intent(this, AddScheduleActivity::class.java)
            startActivity(intent)
        }



    }

//    private fun Delete(){
//
//
////        Builder.setPositiveButton(android.R.string.yes) { function, which ->
////            val snackbar = Snackbar.make(this, "Movie Deleted", Snackbar.LENGTH_INDEFINITE)
////            snackbar.setAction("Dismiss") { snackbar.dismiss() }
////            snackbar.setActionTextColor(Color.WHITE)
////            snackbar.setBackgroundTint(Color.GRAY)
////            snackbar.show()
////
////            //remove
//////            Globalvar.listpeternakan.removeAt(position)
////            finish()
////        }
//    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


}