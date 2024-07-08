package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.ExpenseEntity

@Dao
interface ExpenseDao {
    @Insert
    suspend fun insert(user: ExpenseEntity)

    @Query("SELECT * FROM expense")
    suspend fun getAll(): List<ExpenseEntity>

    @Query("SELECT SUM(amount) FROM expense")
    suspend fun totalExpense(): Int

    @Query("SELECT SUM(amount) FROM expense WHERE payment = 'Cash'")
    suspend fun getAmounCash(): String
}
