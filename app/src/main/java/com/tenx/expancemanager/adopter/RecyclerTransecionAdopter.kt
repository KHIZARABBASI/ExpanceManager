package com.tenx.expancemanager.adopter


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.R
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.database.entity.TransctionEntity
import com.tenx.expancemanager.databinding.RowExpanseLayoutBinding
import com.tenx.expancemanager.databinding.RowFinanceCategoryLayoutBinding
import com.tenx.expancemanager.databinding.RowIncomeLayoutBinding

class RecyclerTransecionAdopter(
    private val context: Context,
    private val mList: ArrayList<TransctionEntity>
) : RecyclerView.Adapter<RecyclerTransecionAdopter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowExpanseLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mList[position]

        if (item.type == "income") {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.earn))
        } else {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.spend))
        }

        holder.binding.ivCategory.setImageResource(item.imgCategory)
        holder.binding.ivPaymentMethod.setImageResource(item.img)
        holder.binding.tvCatagory.text = item.category
        holder.binding.tvExpense.text = item.amount.toString()
        holder.binding.tvDate.text = item.date
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(val binding: RowExpanseLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
