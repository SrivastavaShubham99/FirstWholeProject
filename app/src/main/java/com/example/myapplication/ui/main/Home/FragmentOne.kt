package com.example.myapplication.ui.main.Home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.base.BaseFragment
import com.example.myapplication.databinding.FragmentOneBinding

class FragmentOne:BaseFragment<FragmentOneBinding>() {
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentOneBinding {
        return FragmentOneBinding.inflate(layoutInflater,container,false)
    }

    override fun onViewInitialized() {
        TODO("Not yet implemented")
    }
}