package com.example.myapplication.ui.main

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.View
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
import android.os.Handler
import android.os.Message
import com.example.myapplication.ui.main.Home.ResourceActivity
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
        listerners()
    }
    private fun obViewModel(){
        myViewmodel.getUsers()
    }

    private fun listerners(){
        binding.btn.setOnClickListener(object : View.OnClickListener{
            override fun onClick(v: View?) {
               var intent= Intent(applicationContext,ResourceActivity::class.java)
                startActivity(intent)
            }

        })
    }
   private fun buildUI(){
       myViewmodel.userLiveData.observe(this,mObserver)
       binding.recycler.adapter=userListAdapter
       binding.progressBarCyclic.visibility= View.VISIBLE
       binding.recycler.layoutManager=LinearLayoutManager(this)


   }

    private fun handleRequest(user: ApiResponse<ResponseUserList>){
        when(user.status){
            ApiResponse.Status.LOADING ->{
                Toast.makeText(this,"Bug",Toast.LENGTH_SHORT).show()
            }
            ApiResponse.Status.SUCCESS ->{
                Log.d("TAG","VALUE : ${userList}")
                binding.progressBarCyclic.visibility= View.GONE
                userListAdapter.addUserList((user.data?.data as List<ResponseUserList.Data>?)!!)
            }
            ApiResponse.Status.ERROR ->{
                binding.progressBarCyclic.visibility= View.GONE
                Toast.makeText(this,"Super Bug",Toast.LENGTH_SHORT).show()
            }
        }
    }
}