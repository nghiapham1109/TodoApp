package com.example.noteapp.activities


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.R
import com.example.noteapp.adapter.*
import com.example.noteapp.databinding.ActivityMainBinding
//import com.example.noteapp.databinding.ActivityMainBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(), noteClickInterface, noteDeleteInterface {
    private lateinit var navController: NavController
    lateinit var noteViewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(this.layoutInflater)
        setContentView(binding.root)
        var navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        navController = navHostFragment.findNavController()
    }


    override fun onClick(note: Note) {
        val intent = Intent(this, UpdateNoteActivity::class.java)
        intent.putExtra("noteType", "Edit")
        intent.putExtra("noteIDNote", note.idNote)
        intent.putExtra("noteIDUser", note.idUser)
        intent.putExtra("noteTitle", note.title)
        intent.putExtra("noteDescription", note.description)
        intent.putExtra("notePriority", note.priority)
        startActivity(intent)
        this.finish()
    }

    override fun onDeleteIconClick(note: Note) {
        noteViewModel.deleteNote(note)
    }
}