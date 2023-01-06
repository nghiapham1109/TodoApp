package com.example.noteapp.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.R
import com.example.noteapp.activities.MainActivity
import com.example.noteapp.databinding.FragmentAddBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class AddFragment : Fragment() {

    private lateinit var binding: FragmentAddBinding
    private val viewModel by activityViewModels<NoteViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAdd.setOnClickListener {
            val IDnote = binding.etIDNote.text.toString()
            val IDUser = binding.etIDUser.text.toString()

            if(IDnote.isNotEmpty() && IDUser.isNotEmpty()) {
                val Title = binding.etTitle.text.toString()
                val Description = binding.etDescription.text.toString()
                val Priority = binding.etPriority.text.toString()
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val insertNote = Note(IDnote, IDUser, Title, Description, Priority, currentDateAndTime)
                viewModel.insertNote(insertNote)
            }
            findNavController().navigate(R.id.action_addFragment_to_homeFragment)

        }

        binding.btBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}