package com.example.myapplication.ui.main.adapters

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import com.example.myapplication.ui.main.Home.FragmentOne
import com.example.myapplication.ui.main.Home.FragmentTwo

class SamplePagerAdapter( frgMng:FragmentManager):FragmentPagerAdapter(frgMng){

     val fragmentList=ArrayList<Fragment>()
    override fun getItem(position: Int): Fragment {
        return when(0){
            0 -> FragmentOne()
            1 -> FragmentTwo()
            else -> Fragment()
        }
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(0){
            0 -> return "One"
            1 -> return "Two"
            else -> "Nothing"
        }
    }

}