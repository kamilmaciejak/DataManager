package com.example.datamanager.data

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.example.datamanager.R
import com.example.datamanager.data.example.ExampleData
import com.example.datamanager.data.example.ExampleData1
import com.example.datamanager.data.example.ExampleData2
import com.example.datamanager.database.Data
import com.example.datamanager.databinding.DataListItem1Binding
import com.example.datamanager.databinding.DataListItem2Binding
import kotlinx.android.synthetic.main.data_list_item_1.view.*
import kotlinx.android.synthetic.main.data_list_item_2.view.*

class ExampleDataDiffCallback : DiffUtil.ItemCallback<ExampleData>() {
    override fun areItemsTheSame(oldItem: ExampleData?, newItem: ExampleData?): Boolean {
        return oldItem?.id == newItem?.id
    }

    override fun areContentsTheSame(oldItem: ExampleData?, newItem: ExampleData?): Boolean {
        return oldItem == newItem
    }
}

class DataRecyclerListAdapter(
        private val clickListener: (Data) -> Unit
) : ListAdapter<ExampleData, DataRecyclerListAdapter.ViewHolder>(ExampleDataDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = android.view.LayoutInflater.from(parent.context)
        if (viewType == TYPE_1) {
            return ViewHolder1(DataBindingUtil.inflate(layoutInflater, R.layout.data_list_item_1, parent, false))
        } else {
            return ViewHolder2(DataBindingUtil.inflate(layoutInflater, R.layout.data_list_item_2, parent, false))
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder1 -> holder.itemView.data_list_item_title_text_view_1.text =
                    (getItem(position) as ExampleData1).title
            is ViewHolder2 -> holder.itemView.data_list_item_title_text_view_2.text =
                    (getItem(position) as ExampleData2).longTitle
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) TYPE_1 else TYPE_2
    }

    open class ViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    class ViewHolder1(binding: DataListItem1Binding) : ViewHolder(binding)

    class ViewHolder2(binding: DataListItem2Binding) : ViewHolder(binding)

    companion object {
        val TYPE_1 = 1
        val TYPE_2 = 2
    }
}