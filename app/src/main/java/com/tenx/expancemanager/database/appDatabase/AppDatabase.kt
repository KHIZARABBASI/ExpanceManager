package com.tenx.expancemanager.database.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.model.TagConverter

@Database(entities = [ExpenseEntity::class, IncomeEntity::class], version = 3)
@TypeConverters(TagConverter::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "expense_manager_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

    }
}