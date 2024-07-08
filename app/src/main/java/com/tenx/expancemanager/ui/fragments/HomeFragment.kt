package com.tenx.expancemanager.ui.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerSettingdrawerAdopter
import com.tenx.expancemanager.adopter.RecyclerTransecionAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.dao.IncomeDao
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.databinding.FragmentHomeBinding
import com.tenx.expancemanager.databinding.LayoutNavDrawarHeaderBinding
import com.tenx.expancemanager.databinding.LayoutNavDrawerBodyBinding
import com.tenx.expancemanager.model.SettingDrawerModel
import com.tenx.expancemanager.model.TransactonModel
import com.tenx.expancemanager.utils.DatabaseOpprations
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

class HomeFragment : Fragment() {


    private lateinit var mList: ArrayList<Any>
    private lateinit var adopter: RecyclerTransecionAdopter
    private lateinit var expenseDao: ExpenseDao
    private lateinit var incomeDao: IncomeDao
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
        db = AppDatabase.getDatabase(requireContext())
        expenseDao = db.expenseDao()
        incomeDao = db.incomeDao()
        adopter = RecyclerTransecionAdopter(arrayListOf())
        binding.rvExpanseList.adapter = adopter // Set adapter initially

        lifecycleScope.launch {
            initVar()
            withContext(Dispatchers.Main) {
                visibiltyOfLayouts()
                binding.rvExpanseList.adapter = adopter
            }
        }

        generateTransactionList()
        selectCurrency()
        clickListner()
        navDrawer()
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
            in 12..16 -> "Good Noon"
            in 17..23 -> "Good Evening"
            else -> "Hello"
        }
            binding.greeting.text = greeting.toString()



    }

    private suspend fun setValues() {
        val totalExpense: Int = expenseDao.totalExpense()
        val totalIncome: Int = incomeDao.totalIncome()
        val totalAmount = totalIncome+totalExpense



        try {
             // Assuming totalExpense() is updated to handle null values
            binding.tvExpense.text = totalExpense.toString()
        } catch (e: Exception) {
            Log.e("Error", "Error getting total expense: ${e.message}")
            binding.tvExpense.text = "0"
        }

        try {

            binding.tvAmountIncome.text = totalIncome.toString()
        }  catch (e: Exception){
            Log.e("Error", "Error getting total expense: ${e.message}")
            binding.tvIncome.text = "0"
        }

        binding.tvAmountBalance.text= totalAmount.toString()
    }


    private fun navDrawer() {
        val binding1: LayoutNavDrawarHeaderBinding by lazy {
            LayoutNavDrawarHeaderBinding.inflate(layoutInflater)
        }
        val mList = ArrayList<SettingDrawerModel>()
        val adopter = RecyclerSettingdrawerAdopter(mList)
//        binding.ndNavigation.set

        mList.add(SettingDrawerModel("Theme", "Device Theme"))
        mList.add(SettingDrawerModel("Currency & Format", "Pkr ()"))
        mList.add(SettingDrawerModel("Daily Reminder", "Pkr ()"))
        mList.add(SettingDrawerModel("Backup Data", "Pkr ()"))
        mList.add(SettingDrawerModel("Import Data", "Pkr ()"))
        mList.add(SettingDrawerModel("Export Data", "Pkr ()"))
        mList.add(SettingDrawerModel("Automatic G drive Backup", "Pkr ()"))

        val customView = LayoutInflater.from(requireContext())
            .inflate(R.layout.layout_nav_drawar_header, binding.ndNavigation, false)
//        binding1.rvSettingItem.layoutManager = LinearLayoutManager(requireContext())
        binding.ndNavigation.addView(customView)
    }

    private fun clickListner() {
        // Open drawer on settings click
        binding.setting.setOnClickListener {
            binding.root.openDrawer(binding.ndNavigation)
        }
    }

    private fun visibiltyOfLayouts() {
        // Handling visibility based on the list size
        if (mList.isNotEmpty()) {
            binding.rvExpanseList.visibility = View.VISIBLE
            binding.clNoItem.visibility = View.INVISIBLE
        } else {
            binding.rvExpanseList.visibility = View.INVISIBLE
            binding.clNoItem.visibility = View.VISIBLE
        }
        adopter.notifyDataSetChanged()
    }

    private fun generateTransactionList() {
        // Implement this method
    }

////        adopter.updateList(mList)
////        adopter.notifyDataSetChanged()
//
//        // Update the RecyclerView adapter on the main thread
//        withContext(Dispatchers.Main) {
//            adopter.updateList(mList)
//        }
//        adopter.notifyDataSetChanged()
//
//
//    }


    private suspend fun initVar() {
        withContext(Dispatchers.IO) {
            val expenses = expenseDao.getAll() as List<ExpenseEntity>
            val incomes = incomeDao.getAll() as List<IncomeEntity>

            // Merge expenses and incomes into a single list of Any
            mList = ArrayList<Any>().apply {
                addAll(expenses)
                addAll(incomes)
            }

            // Define a date formatter
            val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

            // Sort the merged list by date
            mList.sortBy {
                when (it) {
                    is ExpenseEntity -> LocalDate.parse(it.date, formatter)
                    is IncomeEntity -> LocalDate.parse(it.date, formatter)
                    else -> throw IllegalArgumentException("Unknown type")
                }
            }
        }

        // Update the RecyclerView adapter on the main thread
        withContext(Dispatchers.Main) {
            adopter.updateList(mList)
        }
    }


    private fun selectCurrency() {
        // Retrieve the currency symbol from SharedPreferences
        val sharedPreference =
            requireContext().getSharedPreferences("currency_symbol", MODE_PRIVATE)
        val currencySymbol = sharedPreference.getString("symbol", "default_symbol")

        // Set the retrieved value to the TextView
        binding.currencySymbolExpence.text = currencySymbol
        binding.currencySymbolIncome.text = currencySymbol
        binding.currencySymbolBalance.text = currencySymbol
    }
}

