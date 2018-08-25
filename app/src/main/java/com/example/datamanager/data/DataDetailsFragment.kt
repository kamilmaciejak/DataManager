package com.example.datamanager.data

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.datamanager.DataManagerApplication
import com.example.datamanager.R
import com.example.datamanager.database.Data
import kotlinx.android.synthetic.main.fragment_data_details.*

class DataDetailsFragment : Fragment() {

    companion object {
        const val DATA_FRAGMENT_DATA_ID = "DATA_FRAGMENT_DATA_ID"

        @JvmStatic
        fun newInstance(dataId: Long? = null) =
                DataDetailsFragment().apply {
                    dataId?.let {
                        arguments = Bundle().apply {
                            putLong(DATA_FRAGMENT_DATA_ID, it)
                        }
                    }
                }
    }

    private var dataId: Long? = null
    private var listener: DataInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { dataId = it.getLong(DATA_FRAGMENT_DATA_ID) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_data_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataId?.let { loadDataDetails() }
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
        data_details_save_btn.setOnClickListener { saveDataDetails() }
        dataId?.let {
            data_details_delete_btn.setOnClickListener { deleteDataDetails() }
        }
    }

    private fun loadDataDetails() {
        activity?.let { activity ->
            val daoSession = (activity.application as DataManagerApplication).daoSession
            val dataDao = daoSession?.dataDao
            dataDao?.let { dao ->
                dao.load(dataId).let { data ->
                    data_details_title_text_view.text = data.title
                    data_details_title_edit_text.setText(data.title)
                    data_details_text_edit_text.setText(data.text)
                    data_details_pin_check_box.isChecked = data.pinActive
                    data_details_delete_btn.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun saveDataDetails() {
        val title = data_details_title_edit_text.text.toString()
        val text = data_details_text_edit_text.text.toString()
        val pinActive = data_details_pin_check_box.isChecked
        saveDataDetails(title, text, pinActive)
        showData()
    }

    private fun saveDataDetails(title: String, text: String, pinActive: Boolean) {
        activity?.let { activity ->
            val daoSession = (activity.application as DataManagerApplication).daoSession
            val dataDao = daoSession?.dataDao
            dataDao?.let { dao ->
                val data = Data()
                data.title = title
                data.text = text
                data.pinActive = pinActive
                dataId?.let {
                    data.id = it
                }
                dao.save(data)
            }
        }
    }

    private fun deleteDataDetails() {
        activity?.let { activity ->
            val daoSession = (activity.application as DataManagerApplication).daoSession
            val dataDao = daoSession?.dataDao
            dataDao?.let { dao ->
                dataId?.let { dao.deleteByKey(it) }
            }
        }
        showData()
    }

    private fun showData() {
        listener?.showData()
    }
}
