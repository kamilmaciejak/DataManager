package com.example.datamanager.data

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.datamanager.R
import com.example.datamanager.database.Data
import kotlinx.android.synthetic.main.data_list_item.view.*

class DataRecyclerAdapter(
        private val dataList: List<Data>,
        private val clickListener: (Data) -> Unit
) : RecyclerView.Adapter<DataRecyclerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.data_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position], clickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val titleTextView: TextView = view.data_list_item_title_text_view

        fun bind(data: Data, clickListener: (Data) -> Unit) {
            titleTextView.text = data.title
            itemView.setOnClickListener { clickListener(data) }
        }
    }
}