package com.example.noteapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey
    val idUser: String,
    val userName: String,
    val password: String
)

@Entity(
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = arrayOf("idUser"),
        childColumns = arrayOf("idUser"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class Note(
    @PrimaryKey
    val idNote: String,
    @ColumnInfo(index = true)
    val idUser: String,
    val title: String,
    val description: String,
    val priority: String,
    val timeStamp: String
)