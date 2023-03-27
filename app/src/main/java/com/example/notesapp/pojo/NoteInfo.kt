package com.example.notesapp.pojo

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.notesapp.utils.convertMillisToTime

@Entity(tableName = "notes_table")
data class NoteInfo(
    @PrimaryKey
    val id: Long?,
    val title: String?,
    val description: String?,
    val time: Long?
) {

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTime(): String {
        return convertMillisToTime(time)
    }
}