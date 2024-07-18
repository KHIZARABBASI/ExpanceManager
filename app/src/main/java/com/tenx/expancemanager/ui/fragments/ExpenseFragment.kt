package com.tenx.expancemanager.ui.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RvExpanseCategoryAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.dao.TransctionDao
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.databinding.FragmentExpenseBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetCategoryBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetPaymentMethodBinding
import com.tenx.expancemanager.ui.activity.DashboardActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseFragment : Fragment(),RvExpanseCategoryAdopter.OnItemClickListener {
    private lateinit var binding: FragmentExpenseBinding
    private lateinit var cal: Calendar
    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao
    private lateinit var expenseDao: ExpenseDao
    private lateinit var tarnsectionDao : TransctionDao
    private lateinit var callback: OnBackPressedCallback
    private lateinit var dialog: Dialog
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private var cashAmount: String = "0.0"


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseBinding.inflate(inflater, container, false)
        initVar()
        onClickListener()

        return binding.root
    }

    private fun onClickListener() {
        binding.tvDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.clPayment.setOnClickListener {}

        binding.tvTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.tvTime.text = SimpleDateFormat("h:mm a", Locale.US).format(cal.time)
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }


        binding.clPayment.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val bindingBottomSheet = LayoutBottomSheetPaymentMethodBinding.inflate(layoutInflater)
            dialog.setContentView(bindingBottomSheet.root)
            bindingBottomSheet.close.setOnClickListener {
                dialog.dismiss()
            }
            dialog.show()

            bindingBottomSheet.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                val selectedRadioButton: RadioButton = group.findViewById(checkedId)
                binding.tvCash.text = selectedRadioButton.text.toString()
                dialog.dismiss()
            }

            bindingBottomSheet.btnSwitch.setOnCheckedChangeListener { _, isChecked ->
                bindingBottomSheet.cashAmount.text = if (isChecked) {
                    cashAmount
                } else {
                    getString(R.string.encript)
                }
            }
        }

        binding.clCategory.setOnClickListener {

            showCategoryDialog()
        }


        binding.saveExpense.setOnClickListener {
            val amount = binding.etAmount.text.toString().toIntOrNull() ?: 0
            val date = binding.tvDate.text.toString()
            val time = binding.tvTime.text.toString()
            val category = binding.tvOther.text.toString()
            val payment = binding.tvCash.text.toString()
            val notes = binding.etNote.text.toString()
            val tag = "Test"
            val img = R.drawable.icon_bank
            val imgCategory = R.drawable.category

            if (amount > 0) {
                saveUser(amount, date, time, category, payment, notes, tag, img, imgCategory)
            } else {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

    private fun initVar() {
        db = AppDatabase.getDatabase(requireContext())
        expenseDao = db.expenseDao()
        tarnsectionDao = db.transectionDao()
        expenseCategoryDao = db.expenseCategoryDao()


        callback = object : OnBackPressedCallback(true /* enabled by default */) {
            override fun handleOnBackPressed() {
                requireContext().startActivity(
                    Intent(
                        requireContext(),
                        DashboardActivity::class.java
                    )
                )
            }
        }


        // Add callback to the back press dispatcher
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        cal = Calendar.getInstance()
        binding.tvDate.text =
            SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis())
        binding.tvTime.text =
            SimpleDateFormat("h:mm a", Locale.US).format(System.currentTimeMillis())

        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.tvDate.text = sdf.format(cal.time)
        }

        lifecycleScope.launch {
            cashAmount = withContext(Dispatchers.IO) {
                expenseDao.totalExpense().toString()
            }
        }
    }

    private fun saveUser(
        amount: Int,
        date: String,
        time: String,
        category: String,
        payment: String,
        note: String,
        tag: String,
        img: Int,
        imgCategory: Int
    ) {
        val user =
            ExpenseEntity(0, amount, date, time, category, payment, note, tag, img, imgCategory)
        val user1 = TransctionEntity(0, amount, date, time, category, payment, note, tag, img, imgCategory,"expense")
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                tarnsectionDao.insert(user1)
                expenseDao.insert(user)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Saved Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Log.e("saveUserError", "Error saving user: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to save user", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    private fun showCategoryDialog() {
        dialog = BottomSheetDialog(requireContext())
        val bindingBottomSheet = LayoutBottomSheetCategoryBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBottomSheet.root)

        bindingBottomSheet.close.setOnClickListener {
            dialog.dismiss()
        }

        lifecycleScope.launch {
            try {
                val mList = expenseCategoryDao.getAll() as ArrayList<ExpenseCategoryEntity>
                val adapter = RvExpanseCategoryAdopter(requireContext(),mList,this@ExpenseFragment)
                bindingBottomSheet.rvCategory.adapter = adapter
                dialog.show()
            } catch (e: Exception) {
                Log.e("ExpenseFragment", "Error fetching categories: ${e.message}")
            }
        }
    }

    override fun onItemClick(item: ExpenseCategoryEntity) {
        binding.tvOther.text = item.name
        dialog.dismiss()
    }
}