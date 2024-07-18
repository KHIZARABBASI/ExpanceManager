package com.tenx.expancemanager.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.edit_category.RvCategoriesAdopter
import com.tenx.expancemanager.database.appDatabase.AppDatabase
import com.tenx.expancemanager.database.dao.ExpenseCategoryDao
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.databinding.ActivityEditCategoryBinding
import com.tenx.expancemanager.model.CategoriesImgModel
import com.tenx.expancemanager.model.CategoryImgModel
import kotlinx.coroutines.launch

class EditCategoryActivity : AppCompatActivity(), RvCategoriesAdopter.OnItemClickListener {
    private lateinit var binding: ActivityEditCategoryBinding
    private lateinit var db: AppDatabase
    private lateinit var expenseCategoryDao: ExpenseCategoryDao
    private  var selectedImageResId: Int =R.drawable.entertainment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityEditCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = AppDatabase.getDatabase(this@EditCategoryActivity)
        expenseCategoryDao = db.expenseCategoryDao()

        lifecycleScope.launch {
            clickListener()
        }


        val mList = ArrayList<CategoriesImgModel>()
        val foodList = ArrayList<CategoryImgModel>()
        val travelList = ArrayList<CategoryImgModel>()
        val shoppingList = ArrayList<CategoryImgModel>()
        val familyList = ArrayList<CategoryImgModel>()
        val entertainmentList = ArrayList<CategoryImgModel>()
        val businessList = ArrayList<CategoryImgModel>()
        val financeList = ArrayList<CategoryImgModel>()
        val medicalList = ArrayList<CategoryImgModel>()
        val utilList = ArrayList<CategoryImgModel>()
        val miscUtilsList = ArrayList<CategoryImgModel>()

        // Add icons to the lists food
        foodList.add(CategoryImgModel(R.drawable.food1))
        foodList.add(CategoryImgModel(R.drawable.food2))
        foodList.add(CategoryImgModel(R.drawable.food3))
        foodList.add(CategoryImgModel(R.drawable.food4))
        foodList.add(CategoryImgModel(R.drawable.food5))
        foodList.add(CategoryImgModel(R.drawable.food6))
        foodList.add(CategoryImgModel(R.drawable.food7))
        foodList.add(CategoryImgModel(R.drawable.food8))
        foodList.add(CategoryImgModel(R.drawable.food9))
        foodList.add(CategoryImgModel(R.drawable.food10))
        foodList.add(CategoryImgModel(R.drawable.food11))
        foodList.add(CategoryImgModel(R.drawable.food12))
        foodList.add(CategoryImgModel(R.drawable.food13))
        foodList.add(CategoryImgModel(R.drawable.food14))
        foodList.add(CategoryImgModel(R.drawable.food15))
        foodList.add(CategoryImgModel(R.drawable.food16))
        foodList.add(CategoryImgModel(R.drawable.food17))


        // Add icons to the lists travel
        travelList.add(CategoryImgModel(R.drawable.travel1))
        travelList.add(CategoryImgModel(R.drawable.travel2))
        travelList.add(CategoryImgModel(R.drawable.travel3))
        travelList.add(CategoryImgModel(R.drawable.travel4))
        travelList.add(CategoryImgModel(R.drawable.travel5))
        travelList.add(CategoryImgModel(R.drawable.travel6))
        travelList.add(CategoryImgModel(R.drawable.travel7))
        travelList.add(CategoryImgModel(R.drawable.travel8))
        travelList.add(CategoryImgModel(R.drawable.travel9))
        travelList.add(CategoryImgModel(R.drawable.travel10))
        travelList.add(CategoryImgModel(R.drawable.travel11))
        travelList.add(CategoryImgModel(R.drawable.travel12))


        // Add icons to the lists shoping
        shoppingList.add(CategoryImgModel(R.drawable.shop1))
        shoppingList.add(CategoryImgModel(R.drawable.shop2))
        shoppingList.add(CategoryImgModel(R.drawable.shop3))
        shoppingList.add(CategoryImgModel(R.drawable.shop4))
        shoppingList.add(CategoryImgModel(R.drawable.shop5))
        shoppingList.add(CategoryImgModel(R.drawable.shop6))
        shoppingList.add(CategoryImgModel(R.drawable.shop7))
        shoppingList.add(CategoryImgModel(R.drawable.shop8))
        shoppingList.add(CategoryImgModel(R.drawable.shop9))
        shoppingList.add(CategoryImgModel(R.drawable.shop10))

        // Add icons to the lists family
        familyList.add(CategoryImgModel(R.drawable.family1))
        familyList.add(CategoryImgModel(R.drawable.family2))
        familyList.add(CategoryImgModel(R.drawable.family3))
        familyList.add(CategoryImgModel(R.drawable.family4))

        // Add icons to the lists entertainment
        entertainmentList.add(CategoryImgModel(R.drawable.entertainment1))
        entertainmentList.add(CategoryImgModel(R.drawable.entertainment2))
        entertainmentList.add(CategoryImgModel(R.drawable.entertainment3))
        entertainmentList.add(CategoryImgModel(R.drawable.entertainment4))

        // Add icons to the lists business
        businessList.add(CategoryImgModel(R.drawable.business1))
        businessList.add(CategoryImgModel(R.drawable.business2))
        businessList.add(CategoryImgModel(R.drawable.business3))
        businessList.add(CategoryImgModel(R.drawable.business4))


        // Add icons to the lists finance
        financeList.add(CategoryImgModel(R.drawable.fiannce1))
        financeList.add(CategoryImgModel(R.drawable.fiannce3))
        financeList.add(CategoryImgModel(R.drawable.fiannce2))
        financeList.add(CategoryImgModel(R.drawable.fiannce4))
        financeList.add(CategoryImgModel(R.drawable.fiannce5))
        financeList.add(CategoryImgModel(R.drawable.fiannce6))
        financeList.add(CategoryImgModel(R.drawable.fiannce7))
        financeList.add(CategoryImgModel(R.drawable.fiannce8))
        financeList.add(CategoryImgModel(R.drawable.fiannce9))
        financeList.add(CategoryImgModel(R.drawable.fiannce10))
        financeList.add(CategoryImgModel(R.drawable.fiannce11))


        // Add icons to the lists medical
        medicalList.add(CategoryImgModel(R.drawable.medical1))
        medicalList.add(CategoryImgModel(R.drawable.medical2))
        medicalList.add(CategoryImgModel(R.drawable.medical3))
        medicalList.add(CategoryImgModel(R.drawable.medical4))
        medicalList.add(CategoryImgModel(R.drawable.medical5))


        // Add icons to the lists utilites
        utilList.add(CategoryImgModel(R.drawable.utilite1))
        utilList.add(CategoryImgModel(R.drawable.utilite2))
        utilList.add(CategoryImgModel(R.drawable.utilite3))
        utilList.add(CategoryImgModel(R.drawable.utilite4))
        utilList.add(CategoryImgModel(R.drawable.utilite5))
        utilList.add(CategoryImgModel(R.drawable.utilite6))
        utilList.add(CategoryImgModel(R.drawable.utilite7))
        utilList.add(CategoryImgModel(R.drawable.utilite8))


        // Add icons to the lists miscu
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal1))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal2))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal3))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal4))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal5))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal6))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal7))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal8))
        miscUtilsList.add(CategoryImgModel(R.drawable.miscal9))

        // Add categories to the main list
        mList.add(CategoriesImgModel("Food", foodList))
        mList.add(CategoriesImgModel("Travel", travelList))
        mList.add(CategoriesImgModel("Shopping", shoppingList))
        mList.add(CategoriesImgModel("Shopping", familyList))
        mList.add(CategoriesImgModel("Shopping", entertainmentList))
        mList.add(CategoriesImgModel("Shopping", businessList))
        mList.add(CategoriesImgModel("Shopping", financeList))
        mList.add(CategoriesImgModel("Shopping", medicalList))
        mList.add(CategoriesImgModel("Shopping", utilList))
        mList.add(CategoriesImgModel("Shopping", miscUtilsList))

        val adapter = RvCategoriesAdopter(this, mList, this@EditCategoryActivity)
        binding.rvEdit.adapter = adapter

        try {
            Toast.makeText(this, "Successful", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            Log.e("EditCategoryActivity", "Error setting adapter: $e")
        }
    }

    private suspend fun clickListener() {

        binding.fabAddCategry.setOnClickListener {
            lifecycleScope.launch {
                expenseCategoryDao.insert(
                    ExpenseCategoryEntity(
                        0,
                        binding.tvCategoryName.text.toString(),
                        selectedImageResId
                    )
                )
            }

        }

    }

    override fun onItemClick(item: CategoryImgModel) {
        Glide.with(this).load(item.img).into(binding.ciSetCategoryImg)
        selectedImageResId = item.img
    }

}
