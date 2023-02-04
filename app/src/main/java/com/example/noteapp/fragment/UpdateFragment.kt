package com.example.noteapp.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.noteapp.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.databinding.FragmentUpdateBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
//class UpdateFragment : Fragment()
class UpdateFragment : BaseFragment<FragmentUpdateBinding>() {
    private val viewModel by activityViewModels<NoteViewModel>()
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentUpdateBinding
        get() = { inflater, container, attachToParent ->
            FragmentUpdateBinding.inflate(inflater, container, attachToParent)
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val noteIDNote = binding.etIDNote
        val noteIDUser = binding.etIDUser
        val noteTitle = binding.etTitle
        val noteDescription = binding.etDescription
        val notePriority = binding.etPriority
        if (arguments?.getString("noteIDNote") != null) {
            val str = arguments?.getString("noteIDNote")
            noteIDNote.setText(str)
        }
        if (arguments?.getString("noteIDUser") != null) {
            val str = arguments?.getString("noteIDUser")
            noteIDUser.setText(str)
        }
        if (arguments?.getString("noteTitle") != null) {
            val str = arguments?.getString("noteTitle")
            noteTitle.setText(str)
        }
        if (arguments?.getString("noteDescription") != null) {
            val str = arguments?.getString("noteDescription")
            noteDescription.setText(str)
        }
        if (arguments?.getString("notePriority") != null) {
            val str = arguments?.getString("notePriority")
            notePriority.setText(str)
        }
        binding.btnUpdate.setOnClickListener {
            val IDnote = binding.etIDNote.text.toString()
            val IDUser = binding.etIDUser.text.toString()

            if (IDnote.isNotEmpty() && IDUser.isNotEmpty()) {
                val Title = binding.etTitle.text.toString()
                val Description = binding.etDescription.text.toString()
                val Priority = binding.etPriority.text.toString()
                val sdf = SimpleDateFormat("dd MMM, yyyy - HH:mm")
                val currentDateAndTime: String = sdf.format(Date())
                val insertNote =
                    Note(IDnote, IDUser, Title, Description, Priority, currentDateAndTime)
                viewModel.updateNote(insertNote)
            }
            findNavController().navigate(R.id.action_updateFragment_to_homeFragment)

        }
        binding.btBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}