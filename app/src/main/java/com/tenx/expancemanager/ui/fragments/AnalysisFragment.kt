package com.tenx.expancemanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tenx.expancemanager.R
import com.tenx.expancemanager.databinding.FragmentAnalysisBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AnalysisFragment : Fragment() {

    private val binding: FragmentAnalysisBinding by lazy {
        FragmentAnalysisBinding.inflate(layoutInflater)
    }
    private lateinit var calendar: Calendar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment



        calendar = Calendar.getInstance()

        // Set initial month display
        updateCurrentMonth()

        clickListener()


        return binding.root


    }

    private fun clickListener() {
        binding.ivPriviousMonth.setOnClickListener {
            navigateToPreviousMonth()
        }

        binding.ivNextMonth.setOnClickListener {
            navigateToNextMonth()
        }
    }

    private fun updateCurrentMonth() {
        val sdf = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val monthYear = sdf.format(calendar.time)
        binding.tvMonth.text = monthYear
    }

    private fun navigateToPreviousMonth() {
        calendar.add(Calendar.MONTH, -1)
        updateCurrentMonth()
    }

    private fun navigateToNextMonth() {
        calendar.add(Calendar.MONTH, 1)
        updateCurrentMonth()
    }

}