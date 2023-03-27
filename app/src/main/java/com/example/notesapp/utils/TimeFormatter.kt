package com.example.notesapp.utils

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun convertMillisToTime(millis: Long?): String {
    val now = Date(millis!!)
    val calNow = Calendar.getInstance()
    val calCheck = Calendar.getInstance()
    calCheck.time = now
    val format =
        if (calNow.get(Calendar.DAY_OF_YEAR) == calCheck.get(Calendar.DAY_OF_YEAR)) "hh:mm a" else "dd.MM.yyyy"
    val formatter = SimpleDateFormat(format, Locale.getDefault())
    Log.d("test date time", now.hours.toString())
    return formatter.format(now)
}