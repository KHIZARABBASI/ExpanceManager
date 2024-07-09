package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.databinding.RowCircularCategogryLayoutBinding
import com.tenx.expancemanager.databinding.RowFinanceCategoryLayoutBinding

class RecyclerExpanseGridCategoryAdopter(
    private val mList: ArrayList<ExpenseCategoryEntity>
) : RecyclerView.Adapter<RecyclerExpanseGridCategoryAdopter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = RowCircularCategogryLayoutBinding.inflate(
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


    inner class ViewHolder(private val binding: RowCircularCategogryLayoutBinding):
            RecyclerView.ViewHolder(binding.root){

                fun bind(item: ExpenseCategoryEntity) {
                    binding.categoryImg.setImageResource(item.categoryImg)
                    binding.tvCatagoryName.text= item.name
                }
            }


}
