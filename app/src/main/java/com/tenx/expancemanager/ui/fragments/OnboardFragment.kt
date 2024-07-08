package com.tenx.expancemanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tenx.expancemanager.databinding.FragmentOnboardBinding
import com.tenx.expancemanager.model.Page


class OnboardFragment(private val page: Page) : Fragment() {
    private val binding : FragmentOnboardBinding by lazy {
        FragmentOnboardBinding.inflate(layoutInflater)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        binding.ivOnboard.setImageResource(page.image)
        binding.tvOnboardText.text = page.desc


        // Inflate the layout for this fragment
        return binding.root
    }


}