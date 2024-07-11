package com.tenx.expancemanager.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.ActivityEditCategoryBinding
import com.tenx.expancemanager.model.CategoriesImgModel
import com.tenx.expancemanager.model.CategoryImgModel

class EditCategoryActivity : AppCompatActivity() {
    val binding : ActivityEditCategoryBinding by lazy {
        ActivityEditCategoryBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        val mList= ArrayList<CategoriesImgModel>()
        val foodList = ArrayList<CategoryImgModel>()

        foodList.add(CategoryImgModel(R.drawable.tea))
        foodList.add(CategoryImgModel(R.drawable.shrimp))
        foodList.add(CategoryImgModel(R.drawable.salad))
        foodList.add(CategoryImgModel(R.drawable.roasted_chicken))
        foodList.add(CategoryImgModel(R.drawable.pizza))
        foodList.add(CategoryImgModel(R.drawable.noodles))
        foodList.add(CategoryImgModel(R.drawable.fried_egg))
        foodList.add(CategoryImgModel(R.drawable.french_fries))
        foodList.add(CategoryImgModel(R.drawable.food))
        foodList.add(CategoryImgModel(R.drawable.food_cart))
        foodList.add(CategoryImgModel(R.drawable.fast_food))
        foodList.add(CategoryImgModel(R.drawable.drink))
        foodList.add(CategoryImgModel(R.drawable.donut))
        foodList.add(CategoryImgModel(R.drawable.diet))
        foodList.add(CategoryImgModel(R.drawable.cooking))
        foodList.add(CategoryImgModel(R.drawable.chili_pepper))
        foodList.add(CategoryImgModel(R.drawable.biryani))

        mList.add(CategoriesImgModel("name", foodList))

    }
}