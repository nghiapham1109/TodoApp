package com.example.noteapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.work.OneTimeWorkRequest
import androidx.work.WorkRequest
import com.example.noteapp.R
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.model.Note
import com.example.noteapp.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import org.w3c.dom.Text

class NoteAdapter(
    val noteClickInterface: noteClickInterface,
    val noteDeleteInterface: noteDeleteInterface,
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {
    private val notes = ArrayList<Note>()
    inner class NoteViewHolder(
        private val binding: NoteItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            val color1 = ContextCompat.getColor(itemView.context, R.color.red)
            val color2 = ContextCompat.getColor(itemView.context, R.color.light_blue_shade)
            val color3 = ContextCompat.getColor(itemView.context, R.color.gray)
            val isCheck = note.priority

            if (isCheck == "1") {
                itemView.setBackgroundColor(color1)
            }
            if (isCheck == "2") {
                itemView.setBackgroundColor(color2)
            }
            if (isCheck == "3") {
                itemView.setBackgroundColor(color3)
            }

            with(binding) {
                with(note) {
                    txtItemTitle.setText(title)
                    txtItemDes.setText(description)
                    txtItemPriority.setText(priority)
                    idTVDate.setText(timeStamp)
                    btnDeleteNote.setOnClickListener {
                        noteDeleteInterface.onDeleteIconClick(this)
                    }
                    itemView.setOnClickListener {
                        noteClickInterface.onClick(this)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = NoteItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(notes[position])
//        holder.bind(viewModel.image)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    fun updateList(newList: List<Note>) {
        notes.clear()
        notes.addAll(newList)
        notifyDataSetChanged()
    }

}

interface noteClickInterface {
    fun onClick(note: Note)
}

interface noteDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

