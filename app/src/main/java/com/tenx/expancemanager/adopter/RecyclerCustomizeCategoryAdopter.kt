package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.databinding.RowSelectCategoryBinding
import com.tenx.expancemanager.model.BottomSheetCategoryModel

class RecyclerCustomizeCategoryAdopter(
    private val mList: ArrayList<BottomSheetCategoryModel>
): RecyclerView.Adapter<RecyclerCustomizeCategoryAdopter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerCustomizeCategoryAdopter.ViewHolder {
        val binding = RowSelectCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    class ViewHolder(val binding: RowSelectCategoryBinding): RecyclerView.ViewHolder(binding.root)

    override fun onBindViewHolder(
        holder: RecyclerCustomizeCategoryAdopter.ViewHolder,
        position: Int
    ) {
        val item = mList[position]
        holder.binding.tvCatagoryText.text = item.name
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
