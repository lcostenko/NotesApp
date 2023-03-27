package com.example.notesapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.notesapp.database.AppDatabase
import com.example.notesapp.pojo.NoteInfo
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)

    private val _selectedItem = MutableLiveData<NoteInfo?>()
    val selectedItem: LiveData<NoteInfo?> = _selectedItem

    private var title: String? = selectedItem.value?.title
    private var description: String? = selectedItem.value?.description
    private var time: Long? = selectedItem.value?.time

    val notesList = db.noteDao().getNotesList()

    fun addNote(note: NoteInfo) {
        viewModelScope.launch {
            db.noteDao().insertNote(note)
        }
    }

    fun deleteNote(note: NoteInfo) {
        viewModelScope.launch {
            db.noteDao().deleteNote(note)
        }
    }

    fun changeTitle(title: String, cTIS: Long) {
        this.title = title
        time = cTIS
    }

    fun changeDescription(description: String, cTIS: Long) {
        this.description = description
        time = cTIS
    }

    fun buildNote(): NoteInfo {
        val note = NoteInfo(
            selectedItem.value?.id,
            title,
            description,
            time
        )
        clearUpNote()
        return if (hasChanges(selectedItem.value!!, note)) {
            note
        } else {
            selectedItem.value!!
        }
    }

    fun setUpNote() {
        title = selectedItem.value?.title
        description = selectedItem.value?.description
        time = selectedItem.value?.time
    }

    private fun clearUpNote() {
        title = null
        description = null
        time = null
    }

    private fun hasChanges(noteSelected: NoteInfo, noteBuilt: NoteInfo): Boolean {
        return noteSelected.title != noteBuilt.title || noteSelected.description != noteBuilt.description
    }

    fun selectingNoteItem(note: NoteInfo?) {
        _selectedItem.value = note
    }
}