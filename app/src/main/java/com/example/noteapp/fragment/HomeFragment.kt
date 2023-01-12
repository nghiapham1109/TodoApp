package com.example.noteapp.fragment

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkRequest
import com.example.noteapp.MyWork
import com.example.noteapp.R
import com.example.noteapp.activities.UpdateNoteActivity
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.adapter.noteClickInterface
import com.example.noteapp.adapter.noteDeleteInterface
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), noteClickInterface, noteDeleteInterface {

    private lateinit var binding: FragmentHomeBinding
    private val noteRVAdapter: NoteAdapter = NoteAdapter(this, this)

    private val viewModel by activityViewModels<NoteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvNote.layoutManager = LinearLayoutManager(context)
        binding.rvNote.adapter = noteRVAdapter


        viewModel.getAllNote().observe(viewLifecycleOwner, Observer { list ->
            list?.let {
                noteRVAdapter.updateList(it)
            }
        })

//        viewModel.getNoteById().observe(viewLifecycleOwner, Observer { list ->
//            list?.let {
//                noteRVAdapter.updateList(it)
//            }
//        })

        binding.btnOpenAddActivity.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    override fun onClick(note: Note) {
        var bundle = Bundle()
        bundle.putString("noteIDNote", note.idNote)
        bundle.putString("noteIDUser", note.idUser)
        bundle.putString("noteTitle", note.title)
        bundle.putString("noteDescription", note.description)
        bundle.putString("notePriority", note.priority)
        findNavController().navigate(R.id.action_homeFragment_to_updateFragment, bundle)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
    }


}