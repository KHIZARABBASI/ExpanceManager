package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.tenx.expancemanager.R
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.dao.IncomeCategoryDao
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.IncomeCategoryEntity
import com.tenx.expancemanager.databinding.ActivitySplashBinding
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao
    private lateinit var incomeCategoryDao: IncomeCategoryDao
    private lateinit var expenseList: ArrayList<ExpenseCategoryEntity>
    private lateinit var incomeList: ArrayList<IncomeCategoryEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        initVar()
        lifecycleScope.launch {
            populateData()
        }

        Handler(Looper.getMainLooper())
            .postDelayed({
                userLogin()
            }, 2000)

    }

    private fun initVar() {
        expenseList = ArrayList()
        incomeList = ArrayList()
        db = AppDatabase.getDatabase(this)
        expenseCategoryDao = db.expenseCategoryDao()
        incomeCategoryDao = db.incomeCategoryDao()
    }

    private fun userLogin() {
        val user = getSharedPreferences("user_login", MODE_PRIVATE)
        val check = user.getBoolean("is_first_time", false)

        if (!check) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            startActivity(Intent(this, DashboardActivity::class.java))
            finish()
        }
    }

    private suspend fun populateData() {
        val populateExCategory = getSharedPreferences("first_time", MODE_PRIVATE)
        val check = populateExCategory.getBoolean("cEC", false)
        Toast.makeText(this, "bool $check", Toast.LENGTH_SHORT).show()

        if (!check) {
            val expenseCategories = listOf(
                ExpenseCategoryEntity(0, "More", R.drawable.more),
                ExpenseCategoryEntity(0, "Food & Dining", R.drawable.food1),
                ExpenseCategoryEntity(0, "Shopping", R.drawable.shop1),
                ExpenseCategoryEntity(0, "Travel", R.drawable.travel1),
                ExpenseCategoryEntity(0, "Entertainment", R.drawable.entertainment1),
                ExpenseCategoryEntity(0, "Health", R.drawable.medical1),
                ExpenseCategoryEntity(0, "Personal Care", R.drawable.miscal9),
                ExpenseCategoryEntity(0, "Education", R.drawable.education_icon),
                ExpenseCategoryEntity(0, "Bill and Utilities", R.drawable.utilite1),
                ExpenseCategoryEntity(0, "Investment", R.drawable.fiannce1),
                ExpenseCategoryEntity(0, "Rent", R.drawable.miscal3),
                ExpenseCategoryEntity(0, "Taxes", R.drawable.utilite8),
                ExpenseCategoryEntity(0, "Insurance", R.drawable.fiannce11),
                ExpenseCategoryEntity(0, "Gifts & Donation", R.drawable.family3)

            )
            val incomeCategories = listOf(
                IncomeCategoryEntity(0, "Other", R.drawable.more),
                IncomeCategoryEntity(0, "Salary", R.drawable.fiannce7),
                IncomeCategoryEntity(0, "Solid Items", R.drawable.fiannce10),
                IncomeCategoryEntity(0, "Coupons", R.drawable.fiannce4),


            )

            // Insert categories into the database
            expenseCategories.forEach { category ->
                expenseCategoryDao.insert(category)
            }

            incomeCategories.forEach{ category ->
                incomeCategoryDao.insert(category)

            }

            val editor = populateExCategory.edit()
            editor.putBoolean("cEC", true)
            editor.apply()
        }

        // Fetch data after insertion
//        expenseList.addAll(expenseCategoryDao.getAll())
//        incomeList.addAll(incomeCategoryDao.getAll())
    }
}