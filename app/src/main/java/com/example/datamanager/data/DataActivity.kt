package com.example.datamanager.data

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.datamanager.PIN_OK
import com.example.datamanager.PREFERENCES
import com.example.datamanager.PREFERENCES_PIN
import com.example.datamanager.R
import com.example.datamanager.pin.PinActivity

class DataActivity : AppCompatActivity(), DataInteractionListener {

    companion object {
        const val PIN_ACTIVITY_REQUEST_CODE = 1
        const val DATA_ACTIVITY_DATA_ID = "DATA_ACTIVITY_DATA_ID"
    }

    private var dataId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data)

        if (savedInstanceState == null) {
            showData()
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        dataId?.let { outState?.putLong(DATA_ACTIVITY_DATA_ID, it) }
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let { dataId = savedInstanceState.getLong(DATA_ACTIVITY_DATA_ID) }
        super.onRestoreInstanceState(savedInstanceState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PIN_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.apply {
                if (getBooleanExtra(PIN_OK, false)) {
                    dataId?.let { showDataDetails(it) }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun showData() {
        if (supportFragmentManager.fragments.isEmpty()) {
            DataFragment.newInstance().apply {
                supportFragmentManager
                        .beginTransaction()
                        .add(R.id.data_content, this)
                        .commit()
            }
        } else {
            supportFragmentManager.popBackStack()
        }
    }

    override fun addDataDetails() {
        DataDetailsFragment.newInstance().apply {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.data_content, this)
                    .addToBackStack(null)
                    .commit()
        }
    }

    override fun checkPinAndShowDataDetails(dataPinActive: Boolean, dataId: Long) {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferences.getString(PREFERENCES_PIN, "").let { pin ->
            if (!pin.isEmpty() && dataPinActive) {
                this.dataId = dataId
                startActivityForResult(
                        Intent(this, PinActivity::class.java),
                        PIN_ACTIVITY_REQUEST_CODE)
            } else {
                showDataDetails(dataId)
            }
        }
    }

    private fun showDataDetails(dataId: Long) {
        DataDetailsFragment.newInstance(dataId).apply {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.data_content, this)
                    .addToBackStack(null)
                    .commit()
        }
    }
}
