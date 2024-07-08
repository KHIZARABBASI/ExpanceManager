package com.tenx.expancemanager.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayoutMediator
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.OnBoardAdoppter
import com.tenx.expancemanager.databinding.ActivityOnboardBinding
import com.tenx.expancemanager.model.Page

class OnboardActivity : AppCompatActivity() {

    private val pagerList = arrayListOf(
        Page(
            R.drawable.img_onboard,
            "dis 1"
        ),
        Page(
            R.drawable.img_onboard2,
            "dis 2"
        ),
        Page(
            R.drawable.img_onboard3,
            "dis 3"
        )


        )

    private val binding: ActivityOnboardBinding by lazy {
        ActivityOnboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.vpOnboard.apply {
            adapter = OnBoardAdoppter(this@OnboardActivity, pagerList)
            (getChildAt(0) as RecyclerView).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        }

        TabLayoutMediator(binding.tlOnboard, binding.vpOnboard) { _, _ -> }.attach()
        binding.fabFarword.setOnClickListener {
            if (binding.vpOnboard.currentItem < binding.vpOnboard.adapter!!.itemCount - 1) {
                binding.vpOnboard.currentItem += 1
            }
            else{
                startActivity(Intent(this, CurrencyActivity::class.java))
                finish()
            }

            binding.fabBackword.setOnClickListener {
                if (binding.vpOnboard.currentItem > 0){
                    binding.vpOnboard.currentItem -= 1
                }
            }

        }
    }
}