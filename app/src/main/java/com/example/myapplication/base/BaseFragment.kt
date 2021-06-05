package com.example.myapplication.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB:ViewBinding>:Fragment(),Baseinterface {
    private var savedInstanceState: Bundle?=null
    lateinit var binding:VB
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding=getViewBinding( inflater,container,savedInstanceState)
        this.savedInstanceState = savedInstanceState
        onViewInitialized()
        return  binding.root
    }

    abstract  fun getViewBinding( inflater: LayoutInflater,
                                  container: ViewGroup?,
                                  savedInstanceState: Bundle?):VB
}