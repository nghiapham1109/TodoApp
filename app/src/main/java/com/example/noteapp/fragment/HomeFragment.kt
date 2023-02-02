package com.example.noteapp.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter
import com.example.noteapp.adapter.noteClickInterface
import com.example.noteapp.adapter.noteDeleteInterface
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
//class HomeFragment : Fragment()
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home), noteClickInterface,
    noteDeleteInterface {

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
        val sharedPref = requireContext().getSharedPreferences("idUser", Context.MODE_PRIVATE)
        val idUser = sharedPref.getString("idUser", sharedPref.toString())
        if (idUser != null) {
            Log.d("First user Test", idUser.toString())
        }
        binding.rvNote.layoutManager = LinearLayoutManager(context)
        binding.rvNote.adapter = noteRVAdapter
//        viewModel.getAllNote().observe(viewLifecycleOwner, Observer { list ->
//            list?.let {
//                noteRVAdapter.updateList(it)
//            }
//        })

        if (idUser != null) {
            viewModel.getNoteById(idUser).observe(viewLifecycleOwner, Observer { list ->
                list?.let {
                    noteRVAdapter.updateList(it)
                }
            })
        }

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