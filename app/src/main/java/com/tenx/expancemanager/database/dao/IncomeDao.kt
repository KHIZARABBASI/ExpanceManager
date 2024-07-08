package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.IncomeEntity

@Dao
interface IncomeDao {
    @Insert
    suspend fun insert(user: IncomeEntity)

    @Query("SELECT * FROM income")
    suspend fun getAll(): List<IncomeEntity>

    @Query("SELECT SUM(amount) FROM income")
    suspend fun totalIncome(): Int

    @Query("SELECT category FROM income")
    suspend fun category(): String
}