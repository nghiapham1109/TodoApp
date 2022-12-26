package com.example.noteapp.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.repository.NoteRepository
import com.example.noteapp.database.repository.UserRepository
import com.example.noteapp.model.Note
import com.example.noteapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application) : AndroidViewModel(application) {
    val user: LiveData<List<User>>

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    init {
        user = userRepository.getAllUser()
    }

    fun insertUser(user: User) = viewModelScope.launch {
        userRepository.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        userRepository.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        userRepository.deleteUser(user)
    }

    fun getAllUser(): LiveData<List<User>> = userRepository.getAllUser()

    fun login(userName: String, passWord: String) {
        val user = userRepository.verifyLoginUser(userName, passWord)
        user?.let {
            loginSuccess.value = true
        } ?: run {
            loginSuccess.value = false
        }
    }


}
