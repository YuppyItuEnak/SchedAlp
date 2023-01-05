package com.example.schedalp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedalp.model.Data
import com.example.schedalp.model.ScheduleData
import com.example.schedalp.model.ScheduleState
import com.example.schedalp.repository.ScheduleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScheduleViewModel @Inject constructor(private val repository: ScheduleRepository): ViewModel() {


    var state by mutableStateOf(ScheduleState())



    val _schedule: MutableLiveData<ScheduleData> by lazy {
        MutableLiveData<ScheduleData>()
    }

    val _schduleId: MutableLiveData<Data> by lazy {
        MutableLiveData<Data>()
    }

    val dataschedule: LiveData<ScheduleData>
    get() = _schedule

    fun getAllScheduleData() = viewModelScope.launch {
        repository.getAllScheduleData().let { response ->
            if( response.isSuccessful){
                _schedule.postValue(response.body() as ScheduleData)
            }else{
                Log.e("Get Data", "Failed!")
            }
        }
    }


    suspend fun DeleteSchedule(id: Int) = repository.DeleteSchedule(id)

    fun UpdateSchedule() = viewModelScope.launch {
        repository.UpdateSchedule(
            schedule_name = state.schedule_name,
            date = state.date,
            waktu = state.waktu,
            activity = state.activity
        )
    }

    fun getSchedule(schdlid: Int) = viewModelScope.launch {
        repository.getSchedule(schdlid).let { response ->
            if( response.isSuccessful){
                _schduleId.postValue(response.body())
            }else{
                Log.e("Get Data", "Failed!")
            }
        }
    }

    fun createSchedule() = viewModelScope.launch{
        repository.createSchedule(
            schedule_name = state.schedule_name,
            date = state.date,
            waktu = state.waktu,
            activity = state.activity
        )
    }

    

}


