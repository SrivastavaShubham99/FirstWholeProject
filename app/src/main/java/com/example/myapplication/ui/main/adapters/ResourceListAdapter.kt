package com.example.myapplication.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.response.ResourceListResponse
import com.example.myapplication.databinding.ResourceListBinding

class ResourceListAdapter(val resourceList : MutableList<ResourceListResponse.Data>):
    RecyclerView.Adapter<ResourceListAdapter.RowHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RowHolder {
        val resourceBinding=ResourceListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RowHolder(resourceBinding)
    }

    override fun getItemCount(): Int {
        return resourceList.size
    }

    override fun onBindViewHolder(holder: RowHolder, position: Int) {
        holder.bind(resourceList[position])
    }

    fun addResource(resource:List<ResourceListResponse.Data>){
        resource.let {
            resourceList.clear()
            resourceList.addAll(it)
            notifyDataSetChanged()
        }
    }

    inner class RowHolder( val  binding : ResourceListBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(response: ResourceListResponse.Data){
            binding.tvOne.text=response.name
            binding.tvTwo.text=response.pantone_value
        }
    }
}