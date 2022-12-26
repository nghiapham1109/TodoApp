package com.example.noteapp.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.noteapp.R
import com.example.noteapp.databinding.ActivityUpdateNoteBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateNoteActivity : AppCompatActivity() {
    private val TAG = "NOTE_VIEW_MODEL"

    private val noteViewModel: NoteViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityUpdateNoteBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_update_note)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val noteType = intent.getStringExtra("noteType")
        if (noteType.equals("Edit")) {
            val noteIDNote = intent.getStringExtra("noteIDNote")
            val noteIDUser = intent.getStringExtra("noteIDUser")
            val noteTitle = intent.getStringExtra("noteTitle")
            val noteDescription = intent.getStringExtra("noteDescription")
            val notePriority = intent.getStringExtra("notePriority")
            binding.btnUpdate.setText("Update Note")
            binding.etIDNote.setText(noteIDNote)
            binding.etIDUser.setText(noteIDUser)
            binding.etTitle.setText(noteTitle)
            binding.etDescription.setText(noteDescription)
            binding.etPriority.setText(notePriority)
        }

        binding.btnUpdate.setOnClickListener {
            val noteIDNote = binding.etIDNote.text.toString()
            val noteIDUser = binding.etIDUser.text.toString()
            if (noteType.equals("Edit")) {
                if (noteIDNote.isNotEmpty() && noteIDUser.isNotEmpty()) {
                    val noteTitle = binding.etTitle.text.toString()
                    val noteDescription = binding.etDescription.text.toString()
                    val notePriority = binding.etPriority.text.toString()
                    val updateNote =
                        Note(noteIDNote, noteIDUser, noteTitle, noteDescription, notePriority)
                    noteViewModel.updateNote(updateNote)
                }
            }
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }
}