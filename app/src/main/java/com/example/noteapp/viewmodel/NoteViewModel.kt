package com.example.noteapp.viewmodel

import android.app.Application
import android.app.Notification
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.noteapp.MyWork
import com.example.noteapp.database.NoteDatabase
import com.example.noteapp.database.repository.NoteRepository
import com.example.noteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository,
    application: Application
) : AndroidViewModel(application) {
    val notes: LiveData<List<Note>>
    private val workManager = WorkManager.getInstance(application)

    init {
        notes = noteRepository.getAllNote()
    }

    fun insertNote(note: Note) = viewModelScope.launch {
        noteRepository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        noteRepository.updateNote(note)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        noteRepository.deleteNote(note)
    }

    fun getAllNote(): LiveData<List<Note>> = noteRepository.getAllNote()

    fun getNoteById(idUser: String) {
        if (idUser == "1") {
            return getNoteById(idUser)
        }
        if (idUser == "2") {
            return getNoteById(idUser)
        }
    }

    internal fun applyNotification(notification: Int) {
        workManager.enqueue(OneTimeWorkRequest.from(MyWork::class.java))
    }
}

