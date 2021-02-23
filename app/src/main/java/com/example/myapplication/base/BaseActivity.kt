package com.example.myapplication.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract  class BaseActivity<VB : ViewBinding> : AppCompatActivity(),Baseinterface{

    private var savedInstanceState:Bundle?=null
    lateinit var binding:VB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState=savedInstanceState
        binding=getViewBinding()
        setContentView(binding.root)
        onViewInitialized()
    }

    abstract  fun getViewBinding():VB

}