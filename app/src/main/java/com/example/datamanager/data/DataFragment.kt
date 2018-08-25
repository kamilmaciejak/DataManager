package com.example.datamanager.data

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.datamanager.DataManagerApplication
import com.example.datamanager.R
import com.example.datamanager.database.Data
import kotlinx.android.synthetic.main.fragment_data.*


class DataFragment : Fragment(), DataRecyclerAdapter.OnItemClickListener {

    companion object {
        @JvmStatic
        fun newInstance() = DataFragment()
    }

    private var listener: DataInteractionListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()
        setupOnClickListeners()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is DataInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement DataInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupOnClickListeners() {
        data_list_add_btn.setOnClickListener { listener?.addDataDetails() }
//        data_list_view.setOnItemClickListener { _, _, position, _ ->
//            listener?.showDataDetails((data_list_view.adapter as DataListAdapter).getItem(position).id)
//        }
    }

    override fun onItemClick(data: Data) {
        listener?.checkPinAndShowDataDetails(data.pinActive, data.id)
    }

    private fun loadData() {
        activity?.let { activity ->
            val daoSession = (activity.application as DataManagerApplication).daoSession
            val dataDao = daoSession?.dataDao
            dataDao?.let { dao ->
                setupDataRecycler(dao.loadAll())
            }
        }
    }

    private fun setupDataRecycler(dataList: List<Data>) {
        data_recycler_view.addItemDecoration(DividerItemDecoration(activity, LinearLayoutManager.VERTICAL))
        data_recycler_view.layoutManager = LinearLayoutManager(activity)
        data_recycler_view.adapter = DataRecyclerAdapter(dataList, this)
    }
}
