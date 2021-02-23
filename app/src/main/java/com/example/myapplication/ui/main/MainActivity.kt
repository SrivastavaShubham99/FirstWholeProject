package com.example.myapplication.ui.main

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.data.response.ResponseUserList
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.network.ApiResponse
import com.example.myapplication.ui.main.adapters.UserListAdapter
import com.example.myapplication.viewmodel.MyViewmodel
import org.koin.android.ext.android.bind
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
     private val userList by lazy { ArrayList<ResponseUserList.Data>() }
    val userListAdapter by lazy { UserListAdapter(userList ) }
    private val myViewmodel by viewModel<MyViewmodel>()
    private val mObserver:Observer<ApiResponse<ResponseUserList>> by lazy {
        Observer<ApiResponse<ResponseUserList>> {
            user -> handleRequest(user)
        }
    }
    override fun getViewBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onViewInitialized() {
        obViewModel()
        buildUI()
    }
    private fun obViewModel(){
        myViewmodel.getUsers()
    }
   private fun buildUI(){
       myViewmodel.userLiveData.observe(this,mObserver)
       binding.recycler.adapter=userListAdapter
       binding.recycler.layoutManager=LinearLayoutManager(this)
   }

    private fun handleRequest(user: ApiResponse<ResponseUserList>){
        when(user.status){
            ApiResponse.Status.LOADING ->{
                Toast.makeText(this,"Bug",Toast.LENGTH_SHORT).show()
            }
            ApiResponse.Status.SUCCESS ->{
                Log.d("TAG","VALUE : ${userList}")
                userListAdapter.addUserList((user.data?.data as List<ResponseUserList.Data>?)!!)
            }
            ApiResponse.Status.ERROR ->{
                Toast.makeText(this,"Super Bug",Toast.LENGTH_SHORT).show()
            }
        }
    }
}