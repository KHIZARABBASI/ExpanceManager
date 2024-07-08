package com.tenx.expancemanager.ui.fragments

import android.app.DatePickerDialog
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
import com.tenx.expancemanager.adopter.RecyclerExpanseCategoryAdopter
import com.tenx.expancemanager.adopter.RecyclerIncomeCategoryAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseDao
import com.tenx.expancemanager.database.dao.IncomeDao
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.databinding.FragmentIncomBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetCategoryBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetPaymentMethodBinding
import com.tenx.expancemanager.model.BottomSheetCategoryModel
import com.tenx.expancemanager.ui.activity.DashboardActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class IncomFragment : Fragment() {
    private val binding: FragmentIncomBinding by lazy {
        FragmentIncomBinding.inflate(layoutInflater)
    }
    private lateinit var cal: android.icu.util.Calendar
    private lateinit var db: AppDatabase
    private lateinit var incomeDao: IncomeDao
    private lateinit var callback: OnBackPressedCallback
    private lateinit var dateSetListener: DatePickerDialog.OnDateSetListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        intvar()

        return binding.root
    }

    private fun intvar() {
        db = AppDatabase.getDatabase(requireContext())
        incomeDao = db.incomeDao()

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

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        cal = android.icu.util.Calendar.getInstance()
        binding.tvDate.text = SimpleDateFormat("dd.MM.yyyy", Locale.US).format(System.currentTimeMillis())
        binding.tvTime.text = SimpleDateFormat("h:mm a", Locale.US).format(System.currentTimeMillis())

        dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(android.icu.util.Calendar.YEAR, year)
            cal.set(android.icu.util.Calendar.MONTH, monthOfYear)
            cal.set(android.icu.util.Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.tvDate.text = sdf.format(cal.time)

    }
}

override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    onClickListener()
}

    private fun onClickListener() {
        binding.tvDate.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListener,
                cal.get(android.icu.util.Calendar.YEAR),
                cal.get(android.icu.util.Calendar.MONTH),
                cal.get(android.icu.util.Calendar.DAY_OF_MONTH)).show()
        }

        binding.tvTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(android.icu.util.Calendar.HOUR_OF_DAY, hour)
                cal.set(android.icu.util.Calendar.MINUTE, minute)
                binding.tvTime.text = SimpleDateFormat("h:mm a", Locale.US).format(cal.time)
            }
            TimePickerDialog(requireContext(), timeSetListener, cal.get(android.icu.util.Calendar.HOUR_OF_DAY), cal.get(
                android.icu.util.Calendar.MINUTE), true).show()
        }
        binding.clCategory.setOnClickListener {
            val dialog = BottomSheetDialog(requireContext())
            val adapter: RecyclerExpanseCategoryAdopter
            val bindingBottomSheet: LayoutBottomSheetCategoryBinding by lazy {
                LayoutBottomSheetCategoryBinding.inflate(layoutInflater)
            }
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.close.setOnClickListener {
                dialog.dismiss()
            }

            val mList: ArrayList<BottomSheetCategoryModel> = ArrayList()

            mList.add(BottomSheetCategoryModel(R.drawable.more, "More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_food, "Food & Dining"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_shop, "Shopping"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_travel, "Travel"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_entertainment, "Entertainment"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_health, "Health"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_personal_care, "Personal Care"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_education, "Education"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_bill, "Bill and Utilities"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_investment, "Investment"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_rent, "Rent"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_tex, "Taxes"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_insurance, "Insurance"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_gift, "Gifts & Donation"))

            adapter = RecyclerExpanseCategoryAdopter(mList)
            bindingBottomSheet.rvCategory.adapter = adapter

            dialog.show()
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
                saveUser(amount, date, time, category, payment, notes, tag, img, imgCategory)
            } else {
                Toast.makeText(requireContext(), "Please enter a valid amount", Toast.LENGTH_SHORT).show()
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
        IncomeEntity(0, amount, date, time, category, payment, note, tag, img, imgCategory)

    lifecycleScope.launch(Dispatchers.IO) {
        try {
            incomeDao.insert(user)
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
}

