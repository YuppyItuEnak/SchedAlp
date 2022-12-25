package com.example.schedalp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.schedalp.adapter.ScheduleAdapter
import com.example.schedalp.databinding.ActivityMainBinding
import com.example.schedalp.viewmodel.ScheduleViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ScheduleAdapter
    private  lateinit var viewModel: ScheduleViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.hide()
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(ScheduleViewModel::class.java)
        viewModel.getAllScheduleData()

        viewModel.dataschedule.observe(this,{ response ->
            binding.mainrv.layoutManager = LinearLayoutManager(this)
            adapter = ScheduleAdapter(response)
            binding.mainrv.adapter = adapter
        })

        binding.create.setOnClickListener {
            val createButton = Intent(this, AddScheduleActivity::class.java)
            startActivity(createButton)
            finish()
        }

    }
}