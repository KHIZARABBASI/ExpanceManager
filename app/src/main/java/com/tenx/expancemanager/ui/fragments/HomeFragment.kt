package com.tenx.expancemanager.ui.fragments

import android.annotation.SuppressLint
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerTransecionAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.dao.IncomeDao
import com.tenx.expancemanager.database.dao.TransctionDao
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.databinding.FragmentHomeBinding
import com.tenx.expancemanager.databinding.LayoutNavDrawarHeaderBinding
import com.tenx.expancemanager.ui.activity.TransactionActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class HomeFragment : Fragment() {

    private lateinit var mList: ArrayList<TransctionEntity>
    private lateinit var adapter: RecyclerTransecionAdopter
    private lateinit var expenseDao: ExpenseDao
    private lateinit var incomeDao: IncomeDao
    private lateinit var transctionDao: TransctionDao
    private lateinit var db: AppDatabase

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        lifecycleScope.launch {
            initVar()
            visibiltyOfLayouts()
        }

        generateTransactionList()
        selectCurrency()
        clickListner()
        setupNavDrawer()
        lifecycleScope.launch {
            setValues()
        }

        greeting()
    }

    private fun greeting() {
        val cal = Calendar.getInstance()
        val hour = cal.get(Calendar.HOUR_OF_DAY)

        val greeting = when (hour) {
            in 0..11 -> "Good Morning"
            in 12..16 -> "Good Afternoon"
            in 17..23 -> "Good Evening"
            else -> "Hello"
        }
        binding.greeting.text = greeting
    }

    private suspend fun setValues() {
        try {
            val totalExpense: Int = expenseDao.totalExpense() ?: 0
            val totalIncome: Int = incomeDao.totalIncome() ?: 0
            val totalAmount = totalIncome + (-totalExpense)

            binding.tvExpense.text = totalExpense.toString()
            binding.tvAmountIncome.text = totalIncome.toString()
            binding.tvAmountBalance.text = totalAmount.toString()
        } catch (e: Exception) {
            Log.e("Error", "Error getting total expense or income: ${e.message}")
            binding.tvExpense.text = "0"
            binding.tvAmountIncome.text = "0"
            binding.tvAmountBalance.text = "0"
        }
    }

    private fun setupNavDrawer() {
        val headerBinding = LayoutNavDrawarHeaderBinding.inflate(layoutInflater)
        binding.ndNavigation.addHeaderView(headerBinding.root)



    }

    private fun clickListner() {
        binding.setting.setOnClickListener {
            binding.root.openDrawer(binding.ndNavigation)
        }

        binding.tvSeeAll.setOnClickListener{
            startActivity(Intent(requireContext(), TransactionActivity::class.java))
        }
    }

    private fun visibiltyOfLayouts() {
        if (mList.isNotEmpty()) {
            binding.rvExpanseList.visibility = View.VISIBLE
            binding.clNoItem.visibility = View.INVISIBLE
            binding.rvExpanseList.adapter = adapter
        } else {
            binding.rvExpanseList.visibility = View.INVISIBLE
            binding.clNoItem.visibility = View.VISIBLE
        }
        adapter.notifyDataSetChanged()
    }

    private fun generateTransactionList() {
        // Implement this method if needed
    }

    private suspend fun initVar() {
        db = AppDatabase.getDatabase(requireContext())
        expenseDao = db.expenseDao()
        incomeDao = db.incomeDao()
        transctionDao = db.transectionDao()
        mList = transctionDao.getAll() as ArrayList<TransctionEntity>
        adapter = RecyclerTransecionAdopter(requireContext(),mList)

        binding.rvExpanseList.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun selectCurrency() {
        val sharedPreference =
            requireContext().getSharedPreferences("currency_symbol", MODE_PRIVATE)
        val currencySymbol = sharedPreference.getString("symbol", "default_symbol")

        binding.currencySymbolExpence.text = currencySymbol
        binding.currencySymbolIncome.text = currencySymbol
        binding.currencySymbolBalance.text = currencySymbol
    }
}