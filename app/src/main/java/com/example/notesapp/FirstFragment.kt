package com.example.notesapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.adapters.NoteAdapter
import com.example.notesapp.databinding.FragmentFirstBinding
import com.example.notesapp.pojo.NoteInfo
import com.example.notesapp.utils.SwipeToDeleteCallback


class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private val sharedViewModel by lazy {
        ViewModelProvider(requireActivity())[NoteViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = NoteAdapter()
        binding.rvNotesList.layoutManager = LinearLayoutManager(activity)
        binding.rvNotesList.adapter = adapter

        val swipeToDeleteCallback = SwipeToDeleteCallback(adapter, sharedViewModel)
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvNotesList)

        sharedViewModel.notesList.observe(viewLifecycleOwner, Observer {
            adapter.noteList = it
        })
        adapter.onNoteClickListener = object : NoteAdapter.OnNoteClickListener {
            override fun onNoteClick(note: NoteInfo) {
                sharedViewModel.selectingNoteItem(note).also { sharedViewModel.setUpNote() }
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}