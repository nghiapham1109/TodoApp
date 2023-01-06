package com.example.noteapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityAddNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteActivity : AppCompatActivity() {
    private val noteViewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityAddNoteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val noteType = intent.getStringExtra("noteType")
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAdd.setOnClickListener {
//            val noteIDNote = binding.etIDNote.text.toString()
//            val noteIDUser = binding.etIDUser.text.toString()
//            if (noteIDNote.isNotEmpty() && noteIDUser.isNotEmpty()) {
//                val noteTitle = binding.etTitle.text.toString()
//                val noteDescription = binding.etDescription.text.toString()
//                val notePriority = binding.etPriority.text.toString()
//                val insertNote =
//                    Note(noteIDNote, noteIDUser, noteTitle, noteDescription, notePriority)
//                noteViewModel.insertNote(insertNote)
//            }
//            startActivity(Intent(applicationContext, MainActivity::class.java))
//            this.finish()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

}
