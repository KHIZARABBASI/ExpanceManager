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
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.databinding.ActivitySplashBinding
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {
    private val binding: ActivitySplashBinding by lazy {
        ActivitySplashBinding.inflate(layoutInflater)
    }
    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao
    private lateinit var mList: ArrayList<ExpenseCategoryEntity>

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
        mList = ArrayList()
        db = AppDatabase.getDatabase(this)
        expenseCategoryDao = db.expenseCategoryDao()
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
            val categories = listOf(
                ExpenseCategoryEntity(0, "More", R.drawable.more),
                ExpenseCategoryEntity(0, "Food & Dining", R.drawable.icon_food),
                ExpenseCategoryEntity(0, "Shopping", R.drawable.icon_shop),
                ExpenseCategoryEntity(0, "Travel", R.drawable.icon_travel),
                ExpenseCategoryEntity(0, "Entertainment", R.drawable.icon_entertainment),
                ExpenseCategoryEntity(0, "Health", R.drawable.icon_health),
                ExpenseCategoryEntity(0, "Personal Care", R.drawable.icon_personal_care),
                ExpenseCategoryEntity(0, "Education", R.drawable.icon_education),
                ExpenseCategoryEntity(0, "Bill and Utilities", R.drawable.icon_bill),
                ExpenseCategoryEntity(0, "Investment", R.drawable.icon_investment),
                ExpenseCategoryEntity(0, "Rent", R.drawable.icon_rent),
                ExpenseCategoryEntity(0, "Taxes", R.drawable.icon_tex),
                ExpenseCategoryEntity(0, "Insurance", R.drawable.icon_insurance),
                ExpenseCategoryEntity(0, "Gifts & Donation", R.drawable.icon_gift)

            )

            // Insert categories into the database
            categories.forEach { category ->
                expenseCategoryDao.insert(category)
            }

            val editor = populateExCategory.edit()
            editor.putBoolean("cEC", true)
            editor.apply()
        }

        // Fetch data after insertion
        mList.addAll(expenseCategoryDao.getAll())
    }
}