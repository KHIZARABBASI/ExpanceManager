package com.tenx.expancemanager.ui.fragments

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adapter.RecyclerBottomSheetCategoryAdopter
import com.tenx.expancemanager.adopter.RecyclerCustomizeCategoryAdopter
import com.tenx.expancemanager.databinding.FragmentExpenseBinding
import com.tenx.expancemanager.databinding.LayoutBottomSheetCategoryBinding
import com.tenx.expancemanager.model.BottomSheetCategoryModel
import java.text.SimpleDateFormat
import java.util.Locale

class ExpenseFragment : Fragment() {
    private val binding:FragmentExpenseBinding by lazy {
        FragmentExpenseBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val cal = Calendar.getInstance()
        binding.tvDate.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())
        binding.tvTime.text = SimpleDateFormat("h:mm a").format(System.currentTimeMillis())

        val dateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.US)
            binding.tvDate.text = sdf.format(cal.time)
        }

        // Set the OnClickListener outside the OnDateSetListener
        binding.tvDate.setOnClickListener {
            DatePickerDialog(requireContext(), dateSetListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }


        //clock

        binding.tvTime.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.tvTime.text = SimpleDateFormat("h:mm a").format(cal.time)
            }
            TimePickerDialog(requireContext(), timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        //bottomsheet

        binding.clCategory.setOnClickListener{
            val dialog = BottomSheetDialog(requireContext())
            val adopter: RecyclerBottomSheetCategoryAdopter
            val bindingBottomSheet: LayoutBottomSheetCategoryBinding by lazy {
                LayoutBottomSheetCategoryBinding.inflate(layoutInflater)
            }



//            dialog.setCancelable(false)
            dialog.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.close.setOnClickListener {
                dialog.dismiss()
            }
//            bindingBottomSheet.rvCategory
            val mList: ArrayList<BottomSheetCategoryModel> = ArrayList()

            mList.add(BottomSheetCategoryModel(R.drawable.more,"More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_food,"Food & Dining"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_shop,"Shopping"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_travel,"More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_entertainment,"More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_health,"More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_personal_care,"More"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_education,"Education"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_bill,"Bill and Utilities"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_investment,"Investment"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_rent,"Rent"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_tex,"Taxes"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_insurance,"Insurance"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_gift,"Gifts & Donation"))
            mList.add(BottomSheetCategoryModel(R.drawable.icon_gift,"Gifts & Donation"))

            adopter = RecyclerBottomSheetCategoryAdopter(mList)

            bindingBottomSheet.rvCategory.adapter=adopter


            dialog.show()
        }

        return binding.root
    }
}
