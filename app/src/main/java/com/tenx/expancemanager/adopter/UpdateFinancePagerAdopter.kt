package com.tenx.expancemanager.adopter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tenx.expancemanager.ui.fragments.ExpenseFragment
import com.tenx.expancemanager.ui.fragments.IncomFragment
import com.tenx.expancemanager.ui.fragments.TransfarFragment

class UpdateFinancePagerAdopter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> {
                ExpenseFragment()
            }

            1 -> {
                IncomFragment()
            }

            else -> {
                TransfarFragment()
            }

        }

    }
}