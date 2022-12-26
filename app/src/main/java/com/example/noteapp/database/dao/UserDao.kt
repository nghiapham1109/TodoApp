package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.example.noteapp.model.User

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("select * from user")
    fun getAllUser(): LiveData<List<User>>

    @Query("SELECT * FROM User WHERE userName LIKE :userName AND passWord LIKE :passWord")
    fun readLoginData(userName: String, passWord: String): User?
}