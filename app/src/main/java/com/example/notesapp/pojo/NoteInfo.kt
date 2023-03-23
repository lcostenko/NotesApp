package com.example.notesapp.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes_table")
data class NoteInfo(
    @PrimaryKey
    val id: Int?,
    val title: String?,
    val description: String?,
    val time: Long?
) { }