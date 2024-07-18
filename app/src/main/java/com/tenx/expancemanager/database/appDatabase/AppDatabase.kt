package com.tenx.expancemanager.database.appDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenx.expancemanager.database.dao.BudgetCategoryDao
import com.tenx.expancemanager.database.dao.BudgetDao
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.dao.IncomeCategoryDao
import com.tenx.expancemanager.database.dao.IncomeDao
import com.tenx.expancemanager.database.dao.TransctionDao
import com.tenx.expancemanager.database.entity.BudgetCategoryEntity
import com.tenx.expancemanager.database.entity.BudgetEntity
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeCategoryEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.model.TagConverter
import com.tenx.expancemanager.utils.DateTypeConvertor

@Database(entities = [ExpenseEntity::class, IncomeEntity::class, ExpenseCategoryEntity::class, IncomeCategoryEntity::class, TransctionEntity::class, BudgetEntity::class, BudgetCategoryEntity::class], version = 16)
//@TypeConverters(TagConverter::class)
//@TypeConverters(DateTypeConvertor::class)

abstract class AppDatabase : RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun incomeDao(): IncomeDao
    abstract fun expenseCategoryDao(): ExpenseCategoryDao
    abstract fun incomeCategoryDao(): IncomeCategoryDao
    abstract fun transectionDao(): TransctionDao
    abstract fun budgetDao(): BudgetDao
    abstract fun budgetCategoryDao(): BudgetCategoryDao


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