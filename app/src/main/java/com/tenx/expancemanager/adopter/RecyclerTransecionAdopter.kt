package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.database.entity.ExpenseEntity
import com.tenx.expancemanager.database.entity.IncomeEntity
import com.tenx.expancemanager.databinding.RowExpanseLayoutBinding
import com.tenx.expancemanager.databinding.RowTransectionLayoutBinding

class RecyclerTransecionAdopter(private var dataList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    companion object {
        private const val VIEW_TYPE_EXPENSE = 1
        private const val VIEW_TYPE_INCOME = 2
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position]) {
            is ExpenseEntity -> VIEW_TYPE_EXPENSE
            is IncomeEntity -> VIEW_TYPE_INCOME
            else -> throw IllegalArgumentException("Invalid type of data $position")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_EXPENSE -> {
                val binding = RowTransectionLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder1(binding)
            }
            VIEW_TYPE_INCOME -> {
                val binding1 = RowExpanseLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                ViewHolder2(binding1)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }





    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder1 -> holder.bind(dataList[position] as ExpenseEntity)
            is ViewHolder2 -> holder.bind(dataList[position] as IncomeEntity)
        }

    }

    override fun getItemCount(): Int = dataList.size


    inner class ViewHolder1(val binding: RowTransectionLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(expense: ExpenseEntity) {
            binding.tvDate.text = expense.date
            binding.tvExpense.text = expense.amount.toString()
            binding.tvCatagory.text = expense.category

        }
    }
    inner class ViewHolder2(val binding: RowExpanseLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(income: IncomeEntity) {
            binding.tvDate.text = income.date
            binding.tvExpense.text = income.amount.toString()
            binding.tvCatagory.text = income.category

        }
    }


    fun updateList(newList: List<Any>) {
        dataList = newList
        notifyDataSetChanged()
    }
}