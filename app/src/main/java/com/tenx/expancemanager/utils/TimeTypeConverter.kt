package com.tenx.expancemanager.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TimeTypeConverter {
    private val format = SimpleDateFormat("h:mm a", Locale.US)

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }

    @TypeConverter
    fun fromTimeString(value: String?): Long? {
        return value?.let { format.parse(it)?.time }
    }

    @TypeConverter
    fun timeToString(value: Long?): String? {
        return value?.let { format.format(Date(it)) }
    }
}
