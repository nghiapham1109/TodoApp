package com.example.noteapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.noteapp.database.dao.NoteDao
import com.example.noteapp.database.dao.UserDao
import com.example.noteapp.model.Note
import com.example.noteapp.model.User

@Database(entities = arrayOf(User::class, Note::class), version = 18, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun getNoteDao(): NoteDao
    abstract fun getUserDao(): UserDao

    companion object {
        @Volatile
        private var instance: NoteDatabase? = null

        fun getInstance(context: Context): NoteDatabase {
            if (instance == null) {
                instance =
                    Room.databaseBuilder(context, NoteDatabase::class.java, "ToDoAppDatabase12")
                        .allowMainThreadQueries()
                        .build()
            }
            return instance!!
        }

    }

}