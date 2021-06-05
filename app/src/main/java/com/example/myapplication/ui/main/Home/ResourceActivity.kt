package com.example.myapplication.ui.main.Home

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.data.response.ResourceListResponse
import com.example.myapplication.databinding.ActivityResourceBinding
import com.example.myapplication.network.ApiResponse
import com.example.myapplication.ui.main.adapters.ResourceListAdapter
import com.example.myapplication.viewmodel.MyViewmodel

class ResourceActivity : BaseActivity<ActivityResourceBinding>() {

    private val mResourceViewModel by viewModel<MyViewmodel>()
    private val resourceList by lazy { ArrayList<ResourceListResponse.Data>() }
    private val resourceAdapter by lazy { ResourceListAdapter(resourceList) }
    private val resourceObserver:Observer<ApiResponse<ResourceListResponse>>by lazy {
        Observer<ApiResponse<ResourceListResponse>>{
            resourceList -> handleResourceResponse(resourceList)
        }
    }

    override fun getViewBinding(): ActivityResourceBinding {
        return ActivityResourceBinding.inflate(layoutInflater)
    }

    override fun onViewInitialized() {
        obViewModel()
        buildUI()
        onClickListeners()
    }

    private fun obViewModel(){
        mResourceViewModel.getResource()

    }

    private fun buildUI(){
        mResourceViewModel.resourceLiveData.observe(this,resourceObserver)
        binding.resourceRecyler.adapter=resourceAdapter
        binding.resourceProgress.visibility=View.VISIBLE
        binding.resourceRecyler.layoutManager=LinearLayoutManager(this)
    }

    private fun onClickListeners(){
        binding.btnClick.setOnClickListener{
            var intent=Intent(this,BasicsFunctionality::class.java).let {
                startActivity(it)
            }
        }
    }

    private fun handleResourceResponse(resourceList:ApiResponse<ResourceListResponse>){
        when(resourceList.status){
            ApiResponse.Status.LOADING -> {
                Log.d("TAG","Loading response !!")
            }
            ApiResponse.Status.SUCCESS ->{
                binding.resourceProgress.visibility= View.GONE
                Log.d("TAG","Is this the value ${resourceList.data}")
                resourceAdapter.addResource((resourceList.data?.data as List<ResourceListResponse.Data>))
            }
            ApiResponse.Status.ERROR -> {
                Log.d("TAG","Error response !!")
                binding.resourceProgress.visibility= View.GONE
            }
        }
    }
}