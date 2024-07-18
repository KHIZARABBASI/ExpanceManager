package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerCustomizeCategoryAdopter
import com.tenx.expancemanager.adopter.RecyclerTransecionAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.dao.TransctionDao
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.databinding.ActivityTransactionBinding
import com.tenx.expancemanager.databinding.LayoutBottomsheetCustomizeBinding
import kotlinx.coroutines.launch

class TransactionActivity : AppCompatActivity() {
    private val binding: ActivityTransactionBinding by lazy {
        ActivityTransactionBinding.inflate(layoutInflater)
    }
    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao
    private lateinit var transctionDao: TransctionDao
    private lateinit var mList: ArrayList<ExpenseCategoryEntity>
    private lateinit var transectinList: ArrayList<TransctionEntity>
    private lateinit var adapter: RecyclerTransecionAdopter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        initVar()

        clickListeners()
    }

    private fun initVar() {
//        mList = ArrayList<ExpenseCategoryEntity>()
        db = AppDatabase.getDatabase(this)
        expenseCategoryDao = db.expenseCategoryDao()
        transctionDao = db.transectionDao()
        transectinList = ArrayList<TransctionEntity>()
        lifecycleScope.launch {
            mList = expenseCategoryDao.getAll() as ArrayList<ExpenseCategoryEntity>
            transectinList = transctionDao.getAll() as ArrayList<TransctionEntity>

            adapter = RecyclerTransecionAdopter(this@TransactionActivity,transectinList)
            setRecycler()
        }


    }

    private fun setRecycler() {
        if (transectinList.isNotEmpty()){
            binding.rvTransection.visibility = View.VISIBLE
            binding.noTrx.visibility = View.INVISIBLE
            binding.rvTransection.adapter = adapter
        } else{
            binding.rvTransection.visibility = View.INVISIBLE
            binding.noTrx.visibility = View.VISIBLE
        }

    }


    private fun clickListeners() {
        binding.ivBack.setOnClickListener {
            finish()
        }

        binding.ivSort.setOnClickListener {
            popupMenu()
        }

        binding.ivCustomize.setOnClickListener {
            val bottomSheet = BottomSheetDialog(this)
            val bindingBottomSheet: LayoutBottomsheetCustomizeBinding by lazy {
                LayoutBottomsheetCustomizeBinding.inflate(layoutInflater)
            }
            bottomSheet.setContentView(bindingBottomSheet.root)

            bindingBottomSheet.rvCategory.layoutManager =
                StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.HORIZONTAL)
            bindingBottomSheet.rvCategory.adapter = RecyclerCustomizeCategoryAdopter(mList)

            bindingBottomSheet.close.setOnClickListener {
                bottomSheet.dismiss()
            }

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
