package com.example.schedalp.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedalp.model.DataX
import com.example.schedalp.model.UserData
import com.example.schedalp.model.UserState
import com.example.schedalp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    var userstate by mutableStateOf(UserState())

    val _user: MutableLiveData<UserData> by lazy {
        MutableLiveData<UserData>()
    }

    val _userId: MutableLiveData<DataX> by lazy {
        MutableLiveData<DataX>()
    }

    val userid: MutableLiveData<DataX> get() = _userId

    val datauser: MutableLiveData<UserData>
        get() = _user

    fun getUser(id: Int) = viewModelScope.launch {
        userRepository.getUser(id).let {
            response ->
            if (response.isSuccessful){
                _user.postValue(response.body())
            }else{
                Log.e("Get Data", "Failed!")
            }
        }
    }


    fun Login() = viewModelScope.launch {
        userRepository.loginUser(
            username = userstate.username,
            password = userstate.password
        )
    }

    fun Register() = viewModelScope.launch{
        userRepository.Register(
            username = userstate.username,
            email = userstate.email,
            password = userstate.password
        )
    }
}