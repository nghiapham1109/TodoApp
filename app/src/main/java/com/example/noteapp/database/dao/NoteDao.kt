package com.example.noteapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.noteapp.model.Note
import com.example.noteapp.model.User

@Dao
interface NoteDao {
    @Insert
    fun insertNote(note: Note)

    @Update
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Query("select * from note")
    fun getAllNote(): LiveData<List<Note>>

    @Query("select * from note where idUser like:idUser")
    fun getNoteById(idUser: String): LiveData<List<Note>>

    @Query("select * from note where title like:title")
    fun searchByTitle(title: String): LiveData<List<Note>>
}

