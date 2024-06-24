package com.tenx.expancemanager.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerCurrencyAdopter
import com.tenx.expancemanager.databinding.ActivityCurrencyBinding
import com.tenx.expancemanager.databinding.ActivityOnboardBinding
import com.tenx.expancemanager.model.CurrencySelectionModel

class CurrencyActivity : AppCompatActivity() {
//    lateinit var mlist: ArrayList<CurrencySelectionModel>
    lateinit var adopter: RecyclerCurrencyAdopter

    private val binding: ActivityCurrencyBinding by lazy {
        ActivityCurrencyBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

//        mlist = CurrencySelectionModel(R.drawable.img_onboard,"pkr","sds")

        binding.conform.setOnClickListener {
            val selectedItem = adopter.getSelectedItem()
            if (selectedItem != null) {
                // Handle the selected item
                // For example, start a new activity or update the UI
                // You can access the selected item's properties like selectedItem.currencyCode, selectedItem.countryName, etc.
            } else {
                // Handle the case where no item is selected
                // For example, show a Toast message
                Toast.makeText(this, "No item selected", Toast.LENGTH_SHORT).show()
            }
        }

        val mlist= ArrayList<CurrencySelectionModel>()
        mlist.add(CurrencySelectionModel(R.drawable.usa,"USD($)","Unided State Dollar"))
        mlist.add(CurrencySelectionModel(R.drawable.india,"INR(₹)","Indian Rupee"))
        mlist.add(CurrencySelectionModel(R.drawable.erop,"EUR(€)","Euro"))
        mlist.add(CurrencySelectionModel(R.drawable.japen,"JPY","Japanese Yan"))
        mlist.add(CurrencySelectionModel(R.drawable.british,"GBP","British Pound"))
        mlist.add(CurrencySelectionModel(R.drawable.australia,"AUD","Austrilan Dollar"))
        mlist.add(CurrencySelectionModel(R.drawable.pakistan,"PKR","Pakistani Rupee"))
        mlist.add(CurrencySelectionModel(R.drawable.china,"CNY","China Yuan Renminbi"))
        mlist.add(CurrencySelectionModel(R.drawable.sweedan,"SEK","Sweden Krona"))
        mlist.add(CurrencySelectionModel(R.drawable.korea,"KRW","South Korea Won"))
        mlist.add(CurrencySelectionModel(R.drawable.norway,"NOK","Norway Krone"))
        mlist.add(CurrencySelectionModel(R.drawable.russia,"RUB","Russia Ruble"))
        mlist.add(CurrencySelectionModel(R.drawable.africa,"ZAR","South Africa Rand"))
        mlist.add(CurrencySelectionModel(R.drawable.turky,"TRY","Turkish Lira"))
        mlist.add(CurrencySelectionModel(R.drawable.brazil,"BRL","Brazilian Real"))
        mlist.add(CurrencySelectionModel(R.drawable.tiwan,"TWD","Taiwan New Dollar"))
        mlist.add(CurrencySelectionModel(R.drawable.thiland,"THB","Thailand Baht"))

        adopter = RecyclerCurrencyAdopter(mlist)
        binding.rvCurrencySelect.adapter = adopter

    }
}