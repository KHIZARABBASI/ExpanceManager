package com.tenx.expancemanager.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.UpdateFinancePagerAdopter
import com.tenx.expancemanager.databinding.ActivityUpdateFinanceBinding

class UpdateFinanceActivity : AppCompatActivity() {
    private val binding: ActivityUpdateFinanceBinding by lazy {
        ActivityUpdateFinanceBinding.inflate(layoutInflater)
    }
    private lateinit var adopter: UpdateFinancePagerAdopter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        //changeStatusBarColor(R.color.btn_gmail_color)

        adopter = UpdateFinancePagerAdopter(supportFragmentManager,lifecycle)

        binding.viewPager.adapter = adopter

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Expanse"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Income"))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Transfer"))

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })

        binding.viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })


    }

    private fun changeStatusBarColor(colorResId: Int) {
        val window = window
        window.statusBarColor = ContextCompat.getColor(this, colorResId)
    }
}