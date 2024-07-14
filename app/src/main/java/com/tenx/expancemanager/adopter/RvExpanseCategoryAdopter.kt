package com.tenx.expancemanager.adopter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.database.entity.ExpenseCategoryEntity
import com.tenx.expancemanager.database.entity.IncomeCategoryEntity
import com.tenx.expancemanager.databinding.RowFinanceCategoryLayoutBinding

class RvExpanseCategoryAdopter(
    private val context: Context,
    private val mList: ArrayList<ExpenseCategoryEntity>,
    private val itemClickListener: RvExpanseCategoryAdopter.OnItemClickListener
) : RecyclerView.Adapter<RvExpanseCategoryAdopter.ViewHolder>() {


    interface OnItemClickListener{
        fun onItemClick(item: ExpenseCategoryEntity)
    }

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

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(private val binding: RowFinanceCategoryLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: ExpenseCategoryEntity) {
            binding.ivCategory.setImageResource(item.categoryImg)
            binding.tvCatagory.text= item.name
        }
    }



}
