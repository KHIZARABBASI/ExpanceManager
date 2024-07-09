package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.TransctionEntity

@Dao
interface TransctionDao {
    @Insert
    suspend fun insert(user: TransctionEntity)

    @Query("SELECT * FROM allTransection")
    suspend fun getAll(): List<TransctionEntity>

    @Query("SELECT SUM(amount) FROM allTransection")
    suspend fun totalExpense(): Int?


}
