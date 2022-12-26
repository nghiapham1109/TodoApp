package com.example.noteapp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.databinding.FragmentUpdateBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateFragment : Fragment() {
    private lateinit var binding: FragmentUpdateBinding
    private val viewModel by activityViewModels<NoteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnUpdate.setOnClickListener {
            val IDnote = binding.etIDNote.text.toString()
            val IDUser = binding.etIDUser.text.toString()

            if(IDnote.isNotEmpty() && IDUser.isNotEmpty()) {
                val Title = binding.etTitle.text.toString()
                val Description = binding.etDescription.text.toString()
                val Priority = binding.etPriority.text.toString()
                val insertNote = Note(IDnote, IDUser, Title, Description, Priority)
                viewModel.updateNote(insertNote)
            }
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

        }
        binding.btBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}