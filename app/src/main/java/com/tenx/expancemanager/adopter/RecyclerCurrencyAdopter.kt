package com.tenx.expancemanager.adopter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.RowCurrencyLayoutBinding
import com.tenx.expancemanager.model.CurrencySelectionModel


class RecyclerCurrencyAdopter(
    private val mList: ArrayList<CurrencySelectionModel>
) :
    RecyclerView.Adapter<RecyclerCurrencyAdopter.ItemViewHolder>() {


    private var selectedPosition = -1

    inner class ItemViewHolder(val binding: RowCurrencyLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    notifyItemChanged(selectedPosition)
                    selectedPosition = position
                    notifyItemChanged(selectedPosition)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerCurrencyAdopter.ItemViewHolder {
        val binding =
            RowCurrencyLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerCurrencyAdopter.ItemViewHolder, position: Int) {
        val itemModel = mList[position]

        holder.binding.flagImg.setImageResource(itemModel.img)
        holder.binding.currencyCode.text = itemModel.currencyCode
        holder.binding.countryName.text = itemModel.countryName

        if (position == selectedPosition) {
            holder.binding.selected.setImageResource(R.drawable.select)
        } else {
            holder.binding.selected.setImageResource(R.drawable.select_not)
        }

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    fun getSelectedItem(): CurrencySelectionModel? {
        return if (selectedPosition != -1) {
            mList[selectedPosition]
        } else {
            null
        }
    }
}