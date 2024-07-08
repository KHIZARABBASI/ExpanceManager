package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.databinding.RowFinanceCategoryLayoutBinding
import com.tenx.expancemanager.model.BottomSheetCategoryModel

class RecyclerIncomeCategoryAdopter(
    private val mList: ArrayList<BottomSheetCategoryModel>
) : RecyclerView.Adapter<RecyclerIncomeCategoryAdopter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RowFinanceCategoryLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val item = mList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val binding: RowFinanceCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: BottomSheetCategoryModel) {
            binding.ivCategory.setImageResource(item.img)
            binding.tvCatagory.text= item.name
        }
    }
}
