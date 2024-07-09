package com.tenx.expancemanager.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.IncomeCategoryEntity

@Dao
interface IncomeCategoryDao {

    @Query("SELECT * FROM income_category")
    suspend fun getAll(): List<IncomeCategoryEntity>

    @Insert
    suspend fun insert(incomeCategoryEntity: IncomeCategoryEntity)
}