package com.tenx.expancemanager.utils

import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DateTypeConvertor {
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    @TypeConverter
    fun fromDate(date: Date?): String? {
        return date?.let { dateFormat.format(it) }
    }

    @TypeConverter
    fun toDate(dateString: String?): Date? {
        return dateString?.let { dateFormat.parse(it) }
    }
}
