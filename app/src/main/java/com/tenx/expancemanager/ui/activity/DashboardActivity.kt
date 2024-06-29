package com.tenx.expancemanager.ui.activity

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.nameRes
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.ActivityDashboardBinding
import com.tenx.expancemanager.ui.fragments.AccountFragment
import com.tenx.expancemanager.ui.fragments.AnalysisFragment
import com.tenx.expancemanager.ui.fragments.HomeFragment
import com.tenx.expancemanager.ui.fragments.MoreFragment

class DashboardActivity : AppCompatActivity() {

    private val binding: ActivityDashboardBinding by lazy {
        ActivityDashboardBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)


        val item1 = PrimaryDrawerItem().apply { nameRes = R.string.btn_phone; identifier = 1 }
        val item2 = SecondaryDrawerItem().apply { nameRes = R.string.search; identifier = 2 }

        binding.slider.itemAdapter.add(
            item1,
            DividerDrawerItem(),
            item2,
            SecondaryDrawerItem().apply { nameRes = R.string.more }
        )

        // specify a click listener
        binding.slider.onDrawerItemClickListener = { v, drawerItem, position ->
            // do something with the clicked item :D
            false
        }


        clickListner()

        loadFragment(HomeFragment())
        binding.bottomNavigationView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    loadFragment(HomeFragment())
                    true
                }

                R.id.analysis -> {
                    loadFragment(AnalysisFragment())
                    true
                }

                R.id.accounts -> {
                    loadFragment(AccountFragment())
                    true
                }

                R.id.more -> {
                    loadFragment(MoreFragment())
                    true
                }


                else -> {
                    loadFragment(HomeFragment())
                    true
                }
            }
        }
    }

    private fun clickListner() {

        binding.fab.setOnClickListener {
           startActivity(Intent(this, UpdateFinanceActivity::class.java))
        }

    }

    private fun loadFragment(fragment: Fragment) {
        val transition = supportFragmentManager.beginTransaction()
        transition.replace(R.id.container, fragment)
        transition.commit()

    }




}