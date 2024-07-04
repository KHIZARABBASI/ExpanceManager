package com.tenx.expancemanager.utils

import android.content.Context
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseDao

class DatabaseOpprations(context: Context) {

    private val db = AppDatabase
    private val dao = db.getDatabase(context)

    suspend fun getAll(){
        dao.expenseDao().getAll()
    }
    suspend fun totalExpense(): String{
       val v= dao.expenseDao().totalExpense()
        return v
    }



}