package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerCustomizeCategoryAdopter
import com.tenx.expancemanager.databinding.ActivityTransactionBinding
import com.tenx.expancemanager.databinding.LayoutBottomsheetCustomizeBinding
import com.tenx.expancemanager.model.BottomSheetCategoryModel

class TransactionActivity : AppCompatActivity() {
    private val binding: ActivityTransactionBinding by lazy {
        ActivityTransactionBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        clicklistners()
    }

    private fun clicklistners() {
        binding.ivBack.setOnClickListener {
            finish()
        }


        binding.ivSort.setOnClickListener {
            popupMenu()
        }

        binding.ivCustomize.setOnClickListener {

            val bottomSheet = BottomSheetDialog(this)
            val binding: LayoutBottomsheetCustomizeBinding by lazy {
                LayoutBottomsheetCustomizeBinding.inflate(layoutInflater)
            }
            val mList= ArrayList<BottomSheetCategoryModel>()
            val adopter= RecyclerCustomizeCategoryAdopter(mList)

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



            bottomSheet.setContentView(binding.root)

            binding.rvCategory.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
            binding.rvCategory.adapter = adopter

            bottomSheet.show()
        }

        binding.fab.setOnClickListener {
            startActivity(Intent(this, UpdateFinanceActivity::class.java))
        }
    }

    private fun popupMenu() {
        val popUp = PopupMenu(this, binding.ivSort)
        popUp.menuInflater.inflate(R.menu.sort_menu, popUp.menu)
        popUp.show()
    }
}