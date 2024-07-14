package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.tenx.expancemanager.adopter.RecyclerExpanseGridCategoryAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.databinding.ActivityCategoryBinding
import kotlinx.coroutines.launch

class CategoryActivity : AppCompatActivity() {

    val binding: ActivityCategoryBinding by lazy {
        ActivityCategoryBinding.inflate(layoutInflater)
    }

    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        db = AppDatabase.getDatabase(this)
        expenseCategoryDao= db.expenseCategoryDao()
        lifecycleScope.launch {
            try {

                expenseCategoryDao.getAll()
                val mList = expenseCategoryDao.getAll() as ArrayList<ExpenseCategoryEntity>
                val adapter = RecyclerExpanseGridCategoryAdopter(mList)
                binding.rvRecyclerCategory.adapter = adapter
            } catch (e: Exception) {
                Log.e("ExpenseFragment", "Error fetching categories: ${e.message}")
            }
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, EditCategoryActivity::class.java))
        }

    }
}