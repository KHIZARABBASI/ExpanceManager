package com.tenx.expancemanager.model

import androidx.room.TypeConverter
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson

class TagConverter {
    @TypeConverter
    fun fromTagList(tag: ArrayList<TagModel>?): String? {
        if (tag == null) {
            return null
        }
        val gson = Gson()
        val type = object : com.google.gson.reflect.TypeToken<ArrayList<TagModel>>() {}.type
        return gson.toJson(tag, type)
    }

    @TypeConverter
    fun toTagList(tagString: String?): ArrayList<TagModel>? {
        if (tagString == null) {
            return null
        }
        val gson = Gson()
        val type = object : TypeToken<ArrayList<TagModel>>() {}.type
        return gson.fromJson(tagString, type)
    }
}
