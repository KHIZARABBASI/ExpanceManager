package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerCurrencyAdopter
import com.tenx.expancemanager.databinding.ActivityCurrencyBinding
import com.tenx.expancemanager.model.CurrencySelectionModel

class CurrencyActivity : AppCompatActivity() {
    private lateinit var mList: ArrayList<CurrencySelectionModel>
    private lateinit var adopter: RecyclerCurrencyAdopter
    private lateinit var selectedCurrency: String

    private val binding: ActivityCurrencyBinding by lazy {
        ActivityCurrencyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.conform.setOnClickListener {
            val selectedItem = adopter.getSelectedItem()

            if (selectedItem != null) {
                val data = selectedItem
                selectedCurrency = data.currencySymbol

                Toast.makeText(this, "code $selectedCurrency", Toast.LENGTH_SHORT).show()
                val sharedPreference = getSharedPreferences("currency_symbol", MODE_PRIVATE)
                val editor = sharedPreference.edit()
                editor.putString("symbol",selectedCurrency)
                editor.apply()


                // Handle the selected item
                // For example, start a new activity or update the UI
                // You can access the selected item's properties like selectedItem.currencyCode, selectedItem.countryName, etc.
            } else {
                // Handle the case where no item is selected
                // For example, show a Toast message
                Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show()
            }
            startActivity(Intent(this@CurrencyActivity, DashboardActivity::class.java))
        }

        mList = ArrayList()
        mList.add(CurrencySelectionModel(R.drawable.usa, "USD($)", "United States Dollar", "$"))
        mList.add(CurrencySelectionModel(R.drawable.india, "INR(₹)", "Indian Rupee", "₹"))
        mList.add(CurrencySelectionModel(R.drawable.erop, "EUR(€)", "Euro", "€"))
        mList.add(CurrencySelectionModel(R.drawable.japen, "JPY(¥)", "Japanese Yen", "¥"))
        mList.add(CurrencySelectionModel(R.drawable.british, "GBP(£)", "British Pound", "£"))
        mList.add(CurrencySelectionModel(R.drawable.australia, "AUD($)", "Australian Dollar", "$"))
        mList.add(CurrencySelectionModel(R.drawable.pakistan, "PKR(Rs)", "Pakistani Rupee", "Rs"))
        mList.add(CurrencySelectionModel(R.drawable.china, "CNY(¥)", "China Yuan Renminbi", "¥"))
        mList.add(CurrencySelectionModel(R.drawable.sweedan, "SEK(kr)", "Sweden Krona", "kr"))
        mList.add(CurrencySelectionModel(R.drawable.korea, "KRW(₩)", "South Korean Won", "₩"))
        mList.add(CurrencySelectionModel(R.drawable.norway, "NOK(kr)", "Norwegian Krone", "kr"))
        mList.add(CurrencySelectionModel(R.drawable.russia, "RUB(₽)", "Russian Ruble", "₽"))
        mList.add(CurrencySelectionModel(R.drawable.africa, "ZAR(R)", "South African Rand", "R"))
        mList.add(CurrencySelectionModel(R.drawable.turky, "TRY(₺)", "Turkish Lira", "₺"))
        mList.add(CurrencySelectionModel(R.drawable.brazil, "BRL(R$)", "Brazilian Real", "R$"))
        mList.add(CurrencySelectionModel(R.drawable.tiwan, "TWD(NT$)", "Taiwan New Dollar", "NT$"))
        mList.add(CurrencySelectionModel(R.drawable.thiland, "THB(฿)", "Thai Baht", "฿"))


        adopter = RecyclerCurrencyAdopter(mList)
        binding.rvCurrencySelect.adapter = adopter

        binding.svSearchCurrency.clearFocus()
        binding.svSearchCurrency.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(msg: String): Boolean {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(msg)
                return false
            }
        })
    }

    private fun filter(text: String) {
        // creating a new array list to filter our data.
        val filteredlist: ArrayList<CurrencySelectionModel> = ArrayList()

        // running a for loop to compare elements.
        for (item in mList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.countryName.lowercase().contains(text.lowercase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show()
        } else {
            // at last we are passing that filtered
            // list to our adapter class
            adopter.filterList(filteredlist)
        }
    }
}
