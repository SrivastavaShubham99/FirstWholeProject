package com.example.myapplication.ui.main.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.response.ResponseUserList
import com.example.myapplication.databinding.UserlistBinding
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import org.koin.ext.getOrCreateScope

class UserListAdapter(val userList:MutableList<ResponseUserList.Data>?): RecyclerView.Adapter<UserListAdapter.RowHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        var mView=UserlistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(mView)
    }

    override fun getItemCount(): Int {
        return userList!!.size
    }

    fun addUserList(users:List<ResponseUserList.Data>){
        users.let {
            userList?.clear()
            userList?.addAll(it)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(userList!!.get(position))
    }

    inner class RowHolder(val binding: UserlistBinding) :RecyclerView.ViewHolder(binding.root){
        fun  bind(users:ResponseUserList.Data){
            var imageUrl:String=users.avatar!!
            Picasso.with(binding.root.context).load(imageUrl).resize(100,100).transform(CropCircleTransformation()).into(binding.profileImage)
            binding.textOne.text=users.firstName
            binding.textTwo.text=users.email
        }
    }

}