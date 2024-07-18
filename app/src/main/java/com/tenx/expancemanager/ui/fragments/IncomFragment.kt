package com.tenx.expancemanager.ui.fragments

import android.app.DatePickerDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Intent
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
import com.tenx.expancemanager.adopter.RvIncomeCategoryAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.IncomeCategoryDao
import com.tenx.expancemanager.database.dao.IncomeDao
import com.tenx.expancemanager.database.dao.TransctionDao
import com.tenx.expancemanager.database.entity.IncomeCategoryEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.databinding.FragmentIncomBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetCategoryBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetPaymentMethodBinding
import com.tenx.expancemanager.ui.activity.DashboardActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Locale

class IncomFragment : Fragment(), RvIncomeCategoryAdopter.OnItemClickListener {
    private lateinit var binding: FragmentIncomBinding
    private lateinit var cal: android.icu.util.Calendar
    private lateinit var db: AppDatabase
    private lateinit var tarnsectionDao: TransctionDao
    private lateinit var incomeDao: IncomeDao
    private lateinit var incomeCategoryDao: IncomeCategoryDao
    private lateinit var callback: OnBackPressedCallback
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener
    private var cashAmount: String = "0.0"
    private lateinit var dialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIncomBinding.inflate(inflater, container, false)
        initVars()
        return binding.root
    }

    private fun initVars() {
        db = AppDatabase.getDatabase(requireContext())
        incomeDao = db.incomeDao()
        tarnsectionDao = db.transectionDao()
        incomeCategoryDao = db.incomeCategoryDao()

        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireContext().startActivity(
                    Intent(requireContext(), DashboardActivity::class.java)
                )
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        cal = android.icu.util.Calendar.getInstance()
        binding.tvDate.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis())
        binding.tvTime.text = SimpleDateFormat("h:mm a", Locale.US).format(System.currentTimeMillis())

        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(android.icu.util.Calendar.YEAR, year)
            cal.set(android.icu.util.Calendar.MONTH, monthOfYear)
            cal.set(android.icu.util.Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd.MM.yyyy"
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.tvDate.text = sdf.format(cal.time)
        }

        lifecycleScope.launch {
            cashAmount = withContext(Dispatchers.IO) {
                incomeDao.totalIncome().toString()
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun setupListeners() {
        binding.tvDate.setOnClickListener {
            DatePickerDialog(
                requireContext(), dateSetListener,
                cal.get(android.icu.util.Calendar.YEAR),
                cal.get(android.icu.util.Calendar.MONTH),
                cal.get(android.icu.util.Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.tvTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hour, minute ->
                cal.set(android.icu.util.Calendar.HOUR_OF_DAY, hour)
                cal.set(android.icu.util.Calendar.MINUTE, minute)
                binding.tvTime.text = SimpleDateFormat("h:mm a", Locale.US).format(cal.time)
            }
            TimePickerDialog(
                requireContext(),
                timeSetListener,
                cal.get(android.icu.util.Calendar.HOUR_OF_DAY),
                cal.get(android.icu.util.Calendar.MINUTE),
                true
            ).show()
        }

        binding.clCategory.setOnClickListener {
            showCategoryDialog()
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
                binding.tvMethod.text = selectedRadioButton.text.toString()
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

        binding.saveIncome.setOnClickListener {
            val amount = binding.etAmount.text.toString().toIntOrNull() ?: 0
            val date = binding.tvDate.text.toString()
            val time = binding.tvTime.text.toString()
            val category = binding.tvOther.text.toString()
            val payment = binding.tvMethod.text.toString()
            val notes = binding.etNote.text.toString()
            val tag = "Test"
            val img = R.drawable.icon_bank
            val imgCategory = R.drawable.category

            if (amount > 0) {
                saveIncome(amount, date, time, category, payment, notes, tag, img, imgCategory)
            } else {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT)
                    .show()
            }
        }
    }

//    private fun showCategoryDialog() {
//        dialog = BottomSheetDialog(requireContext())
//        val bindingBottomSheet = LayoutBottomSheetCategoryBinding.inflate(layoutInflater)
//        dialog.setContentView(bindingBottomSheet.root)
//
//        bindingBottomSheet.close.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        lifecycleScope.launch {
//            try {
//                val categories = withContext(Dispatchers.IO) {
//                    incomeCategoryDao.getAll()
//                }
//                val adapter = RvIncomeCategoryAdopter(
//                    requireContext(),
//                    categories as ArrayList<IncomeCategoryEntity>
//                )
//                bindingBottomSheet.rvCategory.adapter = adapter
//                dialog.show()
//            } catch (e: Exception) {
//                Log.e("IncomFragment", "Error fetching categories: ${e.message}")
//            }
//        }
//    }

    private fun showCategoryDialog() {
        dialog = BottomSheetDialog(requireContext())
        val bindingBottomSheet = LayoutBottomSheetCategoryBinding.inflate(layoutInflater)
        dialog.setContentView(bindingBottomSheet.root)

        bindingBottomSheet.close.setOnClickListener {
            dialog.dismiss()
        }

        lifecycleScope.launch {
            try {
                val categories = withContext(Dispatchers.IO) {
                    incomeCategoryDao.getAll()
                }
                val adapter = RvIncomeCategoryAdopter(
                    requireContext(),
                    categories as ArrayList<IncomeCategoryEntity>,
                    this@IncomFragment
                )
                bindingBottomSheet.rvCategory.adapter = adapter
                dialog.show()
            } catch (e: Exception) {
                Log.e("IncomFragment", "Error fetching categories: ${e.message}")
            }
        }
    }

    private fun saveIncome(
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
        val income =
            IncomeEntity(0, amount, date, time, category, payment, note, tag, img, imgCategory)
        val transaction =
            TransctionEntity(0, amount, date, time, category, payment, note, tag, img, imgCategory, "income")

        lifecycleScope.launch(Dispatchers.IO) {
            try {
                tarnsectionDao.insert(transaction)
                incomeDao.insert(income)
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Saved Successfully", Toast.LENGTH_SHORT)
                        .show()
                }
            } catch (e: Exception) {
                Log.e("saveIncomeError", "Error saving income: ${e.message}")
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "Failed to save income", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }

    override fun onItemClick(item: IncomeCategoryEntity) {
        binding.tvOther.text = item.name
        dialog.dismiss()
    }
}
