package com.example.schedalp.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.schedalp.model.DataX
import com.example.schedalp.model.UserState
import com.example.schedalp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository): ViewModel() {

    var userstate by mutableStateOf(UserState())

    val _user: MutableLiveData<ArrayList<DataX>> by lazy {
        MutableLiveData<ArrayList<DataX>>()
    }

    val datauser: LiveData<ArrayList<DataX>>
        get() = _user


    fun Login(username: String, password: String) = userRepository.LoginUser(username, password)

    fun Register() = viewModelScope.launch{
        userRepository.Register(
            username = userstate.username,
            email = userstate.email,
            password = userstate.password
        )
    }
}