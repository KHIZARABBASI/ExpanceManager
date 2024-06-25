package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.ActivityOnboardBinding

class OnboardActivity : AppCompatActivity() {

    private lateinit var fragmentManager: FragmentManager
    private val binding :ActivityOnboardBinding by lazy {
        ActivityOnboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        fragmentManager = supportFragmentManager

        setContentView(binding.root)
        binding.goToSelectCurncy.setOnClickListener{
            startActivity(Intent(this, CurrencyActivity::class.java))
        }


        //indicator code
//        val dotsIndicator = findViewById<DotsIndicator>(R.id.dots_indicator)
//        val viewPager = findViewById<ViewPager>(R.id.view_pager)
//        val adapter = ViewPagerAdapter()
//        viewPager.adapter = adapter
//        dotsIndicator.attachTo(viewPager)

    }
}