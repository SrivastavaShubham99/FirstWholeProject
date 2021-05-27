package com.example.myapplication.ui.main.Auth

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler;
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.SplashLayoutBinding
import com.example.myapplication.ui.main.MainActivity

class SplashActivity : BaseActivity<SplashLayoutBinding>() {
    override fun getViewBinding(): SplashLayoutBinding {
        return SplashLayoutBinding.inflate(layoutInflater)
    }

    override fun onViewInitialized() {
        obViewModel()
        buildUI()
    }

    private fun obViewModel(){

    }

    @SuppressLint("SetTextI18n")
    private fun buildUI(){
        binding.tvSplash.text="Shubham Markets"
        var handler=Handler()
        handler.postDelayed(object : Runnable {
            @SuppressLint("HandlerLeak")
            override fun run() {
                var intent= Intent(applicationContext,MainActivity::class.java)
                startActivity(intent)
            }
        },1000)


    }

}