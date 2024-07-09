package com.tenx.expancemanager.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "expense_category")
data class ExpenseCategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "category")
    val name: String,
    @ColumnInfo(name = "category_img")
    val categoryImg: Int
)
