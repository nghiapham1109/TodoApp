package com.example.noteapp.database.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.dao.NoteDao
import com.example.noteapp.database.dao.UserDao
import com.example.noteapp.model.Note
import com.example.noteapp.model.User
import javax.inject.Inject
import javax.inject.Singleton

class UserRepository constructor(
    private val userDao: UserDao
) {
    suspend fun insertUser(user: User) = userDao.insertUser(user)
    suspend fun updateUser(user: User) = userDao.updateUser(user)
    suspend fun deleteUser(user: User) = userDao.deleteUser(user)
    fun getAllUser(): LiveData<List<User>> = userDao.getAllUser()

    fun getUser(userName: String, passWord: String): User? {
        return userDao.readLoginData(userName, passWord)
    }

}