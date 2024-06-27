package com.tenx.expancemanager.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickListner()
        changeStatusBarColor(R.color.btn_gmail_color)
//        googleAuth()
//

        setContentView(binding.root)

    }



    private fun clickListner() {
        binding.btnLoginGest.setOnClickListener {
            startActivity(Intent(this, OnboardActivity::class.java))
            finish()
        }
    }
    private fun changeStatusBarColor(colorResId: Int) {
        val window = window
        window.statusBarColor = ContextCompat.getColor(this, colorResId)
    }
}