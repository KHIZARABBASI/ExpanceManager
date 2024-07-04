package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.databinding.RowCurrencyLayoutBinding
import com.tenx.expancemanager.databinding.RowTransectionLayoutBinding
import com.tenx.expancemanager.model.TransactonModel
import java.time.LocalDate

class RecyclerTransecionAdopter(val mList: ArrayList<ExpenseEntity>) : RecyclerView.Adapter<RecyclerTransecionAdopter.ViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerTransecionAdopter.ViewHolder {
        val binding = RowTransectionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerTransecionAdopter.ViewHolder, position: Int) {
        val item = mList[position]

        holder.binding.tvDate.text = item.date
//        holder.binding.ivCategory.setImageResource(item.imgCategory)
        holder.binding.tvExpense.text = item.amount.toString()
        holder.binding.tvCatagory.text= item.category
//        holder.binding.ivPaymentMethod.setImageResource(item.img)
    }

    override fun getItemCount(): Int {
         return mList.size
    }
    inner class ViewHolder(val binding: RowTransectionLayoutBinding): RecyclerView.ViewHolder(binding.root)


    fun updateList(newList: ArrayList<ExpenseEntity>) {
        mList.clear()
        mList.addAll(newList)
        notifyDataSetChanged()
    }
}