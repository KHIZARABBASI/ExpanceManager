package com.tenx.expancemanager.ui.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.ActivityDashboardBinding

class DashboardActivity : AppCompatActivity() {

    private val binding: ActivityDashboardBinding by lazy{
        ActivityDashboardBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


    }


}