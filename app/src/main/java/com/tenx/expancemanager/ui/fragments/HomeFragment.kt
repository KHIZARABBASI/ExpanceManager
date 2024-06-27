package com.tenx.expancemanager.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tenx.expancemanager.R
import com.tenx.expancemanager.adopter.RecyclerTransecionAdopter
import com.tenx.expancemanager.databinding.FragmentHomeBinding
import com.tenx.expancemanager.model.TransactonModel
import java.time.LocalDate


class HomeFragment : Fragment() {

    private lateinit var mList : ArrayList<TransactonModel>
    private lateinit var adopter: RecyclerTransecionAdopter

    private val binding: FragmentHomeBinding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mList= ArrayList<TransactonModel>()
        adopter= RecyclerTransecionAdopter(mList)

        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))
        mList.add(TransactonModel(R.drawable.africa,500,"new",LocalDate.now().year.toString(),R.drawable.accounts))

        if (mList.size>0){

            binding.rvExpanseList.visibility = View.VISIBLE;
            binding.clNoItem.visibility= View.INVISIBLE
        }else{
            binding.rvExpanseList.visibility = View.INVISIBLE;
            binding.clNoItem.visibility= View.VISIBLE
        }

        binding.rvExpanseList.adapter = adopter

        // Inflate the layout for this fragment
        return binding.root

    }

}