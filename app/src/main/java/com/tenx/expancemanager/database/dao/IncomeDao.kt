package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity

@Dao
interface IncomeDao {
    @Insert
    suspend fun insert(user: IncomeEntity)

    @Query("SELECT * FROM expense")
    suspend fun getAll(): List<ExpenseEntity>

    @Query("SELECT SUM(amount) FROM expense")
    suspend fun totalIncome(): Int
}