package com.tenx.expancemanager.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.FragmentMoreBinding
import com.tenx.expancemanager.ui.activity.TransactionActivity


class MoreFragment : Fragment() {
    val binding: FragmentMoreBinding by lazy {
        FragmentMoreBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment


        clicklistners()


        return binding.root
    }

    private fun clicklistners() {

        binding.catagoryTransction.setOnClickListener {
            startActivity(Intent(requireContext(),TransactionActivity::class.java))
        }

        binding.catagoryCategories.setOnClickListener {

        }
    }


}