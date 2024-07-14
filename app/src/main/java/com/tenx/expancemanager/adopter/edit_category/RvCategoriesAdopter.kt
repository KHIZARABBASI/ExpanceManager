package com.tenx.expancemanager.adopter.edit_category

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.databinding.RowCategoryEditLayoutBinding
import com.tenx.expancemanager.model.CategoriesImgModel

class RvCategoriesAdopter(
    val context: Context,
    private val mList: ArrayList<CategoriesImgModel>
) : RecyclerView.Adapter<RvCategoriesAdopter.ViewHolder>() {

    inner class ViewHolder(val binding: RowCategoryEditLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowCategoryEditLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]
        holder.binding.title.text = item.name
        val adapter = RvInnerCategoryAdopter(context, item.categoryImg)
        holder.binding.rvRecyclerViewIcons.adapter = adapter
    }

    override fun getItemCount(): Int {
        return mList.size
    }
}
