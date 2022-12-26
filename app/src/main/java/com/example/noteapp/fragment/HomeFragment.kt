package com.example.noteapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.cardview.widget.CardView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val noteRVAdapter: NoteAdapter = NoteAdapter( this, this)

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

        binding.btnOpenAddActivity.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }
    override fun onClick(note: Note) {
        findNavController().navigate(R.id.action_homeFragment_to_updateFragment)
//        intent.putExtra("noteType", "Edit")
//        intent.putExtra("noteIDNote", note.idNote)
//        intent.putExtra("noteIDUser", note.idUser)
//        intent.putExtra("noteTitle", note.title)
//        intent.putExtra("noteDescription", note.description)
//        intent.putExtra("notePriority", note.priority)
    }

    override fun onDeleteIconClick(note: Note) {
        viewModel.deleteNote(note)
    }
}