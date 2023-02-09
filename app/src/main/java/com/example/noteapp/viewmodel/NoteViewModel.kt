package com.example.noteapp.viewmodel

import android.app.Application
import androidx.lifecycle.*
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkManager
import com.example.noteapp.MyWork
import com.example.noteapp.database.ImageInfo
import com.example.noteapp.database.repository.NoteRepository
import com.example.noteapp.model.Note
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    val noteRepository: NoteRepository,
    application: Application
) : AndroidViewModel(application) {
    //
//    val notes: LiveData<List<Note>>
    val searchTitle = MutableLiveData<String>()

    //
    val image = MutableLiveData<ImageInfo>()

    //
    private val workManager = WorkManager.getInstance(application)

    init {
//        notes = noteRepository.getAllNote()
        image.value = ImageInfo("https://bit.ly/2zpY4w4")
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

    fun getNoteById(idUser: String): LiveData<List<Note>> = noteRepository.getNoteById(idUser)

    fun searchByTitle(searchTmp: String, idUser: String): LiveData<List<Note>> = noteRepository
        .searchByTitle(searchTmp, idUser)

    internal fun applyNotification(notification: Int) {
        workManager.enqueue(OneTimeWorkRequest.from(MyWork::class.java))
    }

    fun onAddNoteCLicked() {

    }
}

