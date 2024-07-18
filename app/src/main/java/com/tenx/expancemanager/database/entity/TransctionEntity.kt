package com.tenx.expancemanager.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "allTransection")
data class TransctionEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val iId: Int = 0,
    @ColumnInfo(name = "amount")
    val amount: Int = 0,
    @ColumnInfo(name = "date")
    val date: String,
    @ColumnInfo(name = "time")
    val time: String = "",
    @ColumnInfo(name = "category")
    val category: String? = null,
    @ColumnInfo(name = "payment_method")
    val payment: String? = null,
    @ColumnInfo(name = "notes")
    val note: String? = null,
    @ColumnInfo(name = "tag")
    val tag: String?=null,
    @ColumnInfo(name = "img_category")
    val imgCategory: Int, // Assuming these fields exist
    @ColumnInfo(name = "img")
    val img: Int,
    @ColumnInfo(name = "type")
    val type: String

)
