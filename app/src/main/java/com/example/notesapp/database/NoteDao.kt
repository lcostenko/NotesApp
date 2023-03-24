package com.example.notesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.notesapp.pojo.NoteInfo

@Dao
interface NoteDao {

    @Query("SELECT * FROM notes_table ORDER BY time DESC")
    fun getNotesList(): LiveData<List<NoteInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteInfo)

    @Delete
    suspend fun deleteNote(note: NoteInfo)
}