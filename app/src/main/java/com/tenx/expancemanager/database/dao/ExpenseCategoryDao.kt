package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity

@Dao
interface ExpenseCategoryDao {

    @Query("SELECT * FROM expense_category")
    suspend fun getAll(): List<ExpenseCategoryEntity>

    @Insert
    suspend fun insert(expenseCategoryEntity: ExpenseCategoryEntity)
}