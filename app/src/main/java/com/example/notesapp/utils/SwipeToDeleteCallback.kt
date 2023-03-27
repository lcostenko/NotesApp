package com.example.notesapp.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.NoteViewModel
import com.example.notesapp.adapters.NoteAdapter

class SwipeToDeleteCallback(
    private val adapter: NoteAdapter,
    private val viewModel: NoteViewModel
) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val position = viewHolder.adapterPosition
        viewModel.notesList.value?.get(position)?.let { viewModel.deleteNote(it) }
        adapter.notifyItemRemoved(position)
        adapter.notifyItemRangeChanged(position, adapter.itemCount)
    }
}