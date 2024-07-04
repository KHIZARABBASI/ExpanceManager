package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.databinding.RowFinanceCategoryLayoutBinding
import com.tenx.expancemanager.databinding.RowRecyclerSettingItemBinding
import com.tenx.expancemanager.databinding.RowSelectCategoryBinding
import com.tenx.expancemanager.model.SettingDrawerModel

class RecyclerSettingdrawerAdopter(
    private val mList: ArrayList<SettingDrawerModel>
) : RecyclerView.Adapter<RecyclerSettingdrawerAdopter.ViewHolder>(){
    class ViewHolder(val binding: RowRecyclerSettingItemBinding): RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerSettingdrawerAdopter.ViewHolder {
        val binding = RowRecyclerSettingItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: RecyclerSettingdrawerAdopter.ViewHolder,
        position: Int)
    {
        val item = mList[position]
        holder.binding.tvTheme.text = item.heading
        holder.binding.tvThemeDescription.text = item.description

    }

    override fun getItemCount(): Int {
        return mList.size
    }
}