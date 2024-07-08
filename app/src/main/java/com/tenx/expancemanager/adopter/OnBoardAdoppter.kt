package com.tenx.expancemanager.adopter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tenx.expancemanager.model.Page
import com.tenx.expancemanager.ui.fragments.OnboardFragment

class OnBoardAdoppter(activity: FragmentActivity, private val pagerList: ArrayList<Page>)
    : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return  pagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        return OnboardFragment(pagerList[position])
    }


}