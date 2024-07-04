package com.tenx.expancemanager.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.tenx.expancemanager.model.TagModel

@Entity(tableName = "expense")
data class ExpenseEntity (
    @PrimaryKey(autoGenerate = true)
    val eId: Int = 0,
    @ColumnInfo
    val amount: Int = 0,
    @ColumnInfo
    val date: String = "",
    @ColumnInfo
    val time: String = "",
    @ColumnInfo
    val category: String? = null,
    @ColumnInfo
    val payment: String? = null,
    @ColumnInfo
    val note: String? = null,
    @ColumnInfo
    val tag: String?=null ,
    @ColumnInfo
    val imgCategory: Int , // Assuming these fields exist
    @ColumnInfo
    val img: Int
)
