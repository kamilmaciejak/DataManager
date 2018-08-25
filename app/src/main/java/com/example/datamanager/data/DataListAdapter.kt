package com.example.datamanager.data

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.datamanager.R
import com.example.datamanager.database.Data
import kotlinx.android.synthetic.main.data_list_item.view.*

class DataListAdapter(
        context: Context,
        private val dataList: List<Data>
) : BaseAdapter() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val data = getItem(position)
        val view: View
        val viewHolder: ViewHolder

        if (convertView == null) {
            view = layoutInflater.inflate(R.layout.data_list_item, parent, false)
            viewHolder = ViewHolder().apply {
                titleTextView = view.data_list_item_title_text_view
            }
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.titleTextView.text = data.title
        return view
    }

    override fun getItem(position: Int): Data {
        return dataList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataList.size
    }

    private class ViewHolder {
        lateinit var titleTextView: TextView
    }

}