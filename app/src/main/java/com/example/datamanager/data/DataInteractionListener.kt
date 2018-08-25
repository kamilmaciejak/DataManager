package com.example.datamanager.data

interface DataInteractionListener {
    fun showData()
    fun addDataDetails()
    fun checkPinAndShowDataDetails(dataPinActive: Boolean, dataId: Long)
}