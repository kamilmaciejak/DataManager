package com.example.datamanager.data

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.datamanager.R
import com.example.datamanager.database.Data
import com.example.datamanager.databinding.DataListItemBinding

class DataItemRecyclerAdapter(
        private val dataList: List<Data>,
        private val clickListener: (Data) -> Unit
) : RecyclerView.Adapter<DataItemRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<DataListItemBinding>(LayoutInflater.from(parent.context), R.layout.data_list_item, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.setOnClickListener { clickListener(dataList[position]) }
        holder.binding.executePendingBindings()
//        holder.binding.dataListItemTitleTextView.text = dataList[position].title
    }

    class ViewHolder(val binding: DataListItemBinding) : RecyclerView.ViewHolder(binding.root)
}