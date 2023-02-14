package com.example.noteapp.fragment

import android.os.*
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteapp.BaseFragment
import com.example.noteapp.R
import com.example.noteapp.adapter.*
import com.example.noteapp.databinding.FragmentHomeBinding
import com.example.noteapp.datastore.local.TempData
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS

@AndroidEntryPoint
//class HomeFragment : Fragment()
class HomeFragment : BaseFragment<FragmentHomeBinding>(), noteClickInterface,
    noteDeleteInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentHomeBinding
        get() = { inflater, container, attachToParent ->
            FragmentHomeBinding.inflate(inflater, container, attachToParent)
        }

    private val noteRVAdapter: NoteAdapter = NoteAdapter(this, this)

    private val viewModel by activityViewModels<NoteViewModel>()

    private val searchSubject = PublishSubject.create<String>()

    private fun setUpSearchDebounce() {
        searchSubject.debounce(800, MILLISECONDS).distinctUntilChanged()
            .observeOn((AndroidSchedulers.mainThread()))
            .subscribe({
                viewModel.searchByTitle(it, TempData.idUser)
                    .observe(viewLifecycleOwner, Observer { list ->
                        list?.let {
                            noteRVAdapter.submitList(it)
                        }
                    })
            }, {
                Log.e("HomeFrag", it.toString())
            })
            .also { disposable ->
                viewLifecycleOwner.lifecycle.addObserver(object :
                    LifecycleObserver {
                    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
                    fun onDestroy() {
                        disposable.dispose()
                    }
                })
            }
    }

    private val searchHandler = Handler(Looper.getMainLooper(), Handler.Callback { msg ->
        val searchText = msg.obj as String
        searchSubject.onNext(searchText)
//        viewModel.searchByTitle(searchText, TempData.idUser).observe(viewLifecycleOwner, Observer
//        { list ->
//            list?.let {
//                noteRVAdapter.submitList(it)
//            }
//        })
        true
    })

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvNote.layoutManager = LinearLayoutManager(context)
        binding.rvNote.adapter = noteRVAdapter
        if (TempData.idUser.isNotEmpty()) {
            viewModel.getNoteById(TempData.idUser).observe(viewLifecycleOwner, Observer { list ->
                list?.let {
                    noteRVAdapter.submitList(it)
                }
            })
        }
        setUpSearchDebounce()
        binding.etSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val searchText = p0.toString()
                searchHandler.removeMessages(0)
                searchHandler.sendMessageDelayed(Message.obtain(searchHandler, 0, searchText), 500)
//                viewModel.searchByTitle(p0.toString(), TempData.idUser)
//                    .observe(viewLifecycleOwner, Observer { list ->
//                        list?.let {
//                            noteRVAdapter.submitList(it)
//                        }
//                    })
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val searchTmp = p0.toString()
                viewModel.searchTitle.value = searchTmp
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.searchTitle.value = p0.toString()
            }

        })
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