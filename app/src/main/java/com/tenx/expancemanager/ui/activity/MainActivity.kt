package com.tenx.expancemanager.ui.activity


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tenx.expancemanager.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        clickListner()
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

}