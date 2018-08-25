package com.example.datamanager.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.datamanager.PIN_OK
import com.example.datamanager.PREFERENCES_PIN
import com.example.datamanager.PREFERENCES
import com.example.datamanager.pin.PinActivity
import com.example.datamanager.R
import com.example.datamanager.config.ConfigActivity
import com.example.datamanager.data.DataActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val PIN_ACTIVITY_REQUEST_CODE = 1
        const val MAIN_ACTIVITY_PIN_OK = "MAIN_ACTIVITY_PIN_OK"
    }

    private var pinChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null || !savedInstanceState.getBoolean(MAIN_ACTIVITY_PIN_OK)) {
            checkPin()
        } else {
            pinChecked = true
        }
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putBoolean(MAIN_ACTIVITY_PIN_OK, pinChecked)
        super.onSaveInstanceState(outState)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PIN_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.apply {
                if (getBooleanExtra(PIN_OK, false)) {
                    pinChecked = true
                } else {
                    finish()
                }
            }
        }
    }

    private fun checkPin() {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferences.getString(PREFERENCES_PIN, "").let { pin ->
            if (pin.isEmpty()) {
                pinChecked = true
            } else {
                startActivityForResult(
                        Intent(this, PinActivity::class.java),
                        PIN_ACTIVITY_REQUEST_CODE)
            }
        }
    }

    fun showData(view: View) {
        startActivity(Intent(this, DataActivity::class.java))
    }

    fun showConfig(view: View) {
        startActivity(Intent(this, ConfigActivity::class.java))
    }
}
