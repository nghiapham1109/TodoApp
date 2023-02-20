package com.example.noteapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.*
import com.example.noteapp.R
import com.example.noteapp.adapter.NoteAdapter.NoteViewHolder
import com.example.noteapp.databinding.NoteItemBinding
import com.example.noteapp.model.Note

class NoteAdapter(
    val noteClickInterface: noteClickInterface,
    val noteDeleteInterface: noteDeleteInterface,
) : ListAdapter<Note, NoteViewHolder>(NoteDiffCallback()) {

    inner class NoteViewHolder(
        private val binding: NoteItemBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(note: Note) {
//            binding.note = note
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
                    txtItemTitle.setText("Title: " + title)
                    txtItemDes.setText("Description: " + description)
                    txtItemPriority.setText("Priority: " + priority)
                    idTVDate.setText("Time: " + timeStamp)
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
        holder.bind(getItem(position))
//        holder.bind(viewModel.image)
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.idNote == newItem.idNote
        }
    }

}

interface noteClickInterface {
    fun onClick(note: Note)
}

interface noteDeleteInterface {
    fun onDeleteIconClick(note: Note)
}

