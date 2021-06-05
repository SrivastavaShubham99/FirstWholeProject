package com.example.myapplication.ui.main.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.BasicsFunctionalityBinding
import com.example.myapplication.ui.main.adapters.SamplePagerAdapter

class BasicsFunctionality : BaseActivity<BasicsFunctionalityBinding>() {

    override fun getViewBinding(): BasicsFunctionalityBinding {
        return BasicsFunctionalityBinding.inflate((layoutInflater))
    }

    override fun onViewInitialized() {
        buildUI()
        onClickListeners()
    }

    private fun buildUI(){
        val viewPager=binding.viewPager
        viewPager.adapter=SamplePagerAdapter(supportFragmentManager)
        binding.tabLayout.setupWithViewPager(viewPager)

    }
    private fun onClickListeners(){

    }
}