package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.noteapp.database.repository.UserRepository
import com.example.noteapp.model.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
    application: Application,
//    private val context: Context
) : AndroidViewModel(application) {
    val users: LiveData<List<User>>
    val loginUser: MutableLiveData<User> = MutableLiveData()

    val loginSuccess: MutableLiveData<Boolean> = MutableLiveData()

    init {
        users = userRepository.getAllUser()
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
        userRepository.getUser(userName, passWord)?.let {
            loginUser.value = it
            loginSuccess.value = true
        } ?: run {
            loginSuccess.value = false
        }
//        sharedPreferencesManager.saveUserData(userName, "User data")
    }

//    fun getUserData(userName: String): String {
//        return sharedPreferencesManager.getUserData(userName)
//    }


}
