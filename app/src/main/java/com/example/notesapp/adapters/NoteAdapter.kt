package com.example.notesapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.databinding.NotesItemBinding
import com.example.notesapp.pojo.NoteInfo

class NoteAdapter :
    RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    var noteList: List<NoteInfo> = arrayListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class NoteViewHolder(binding: NotesItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.tvTitle
        val tvDescription = binding.tvDescription
        val tvTime = binding.tvTime
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NoteViewHolder(
            NotesItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList[position]
        holder.tvTitle.text = note.title.toString()
        holder.tvDescription.text = note.description.toString()
        holder.tvTime.text = note.time.toString()
    }
}