package com.example.datamanager.config

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.datamanager.PREFERENCES
import com.example.datamanager.PREFERENCES_PIN
import com.example.datamanager.R
import kotlinx.android.synthetic.main.pin.*

class ConfigActivity : AppCompatActivity() {

    companion object {
        const val PIN_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        loadPin()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun loadPin() {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        showPin(sharedPreferences.getString(PREFERENCES_PIN, ""))
    }

    fun updatePin(view: View) {
        var pin = getPin()
        if (pin.length < PIN_LENGTH) {
            pin += (view as Button).text
            showPin(pin)
        }
    }

    fun deletePin(view: View) {
        var pin = getPin()
        if (!pin.isEmpty()) {
            pin = pin.substring(0, pin.length - 1)
            showPin(pin)
        }
    }

    fun savePin(view: View) {
        var pin = getPin()
        if (pin.isEmpty() || pin.length == PIN_LENGTH) {
            val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
            sharedPreferences.edit().apply {
                putString(PREFERENCES_PIN, pin)
                commit()
                finish()
            }
        }
    }

    private fun getPin(): String = pin_value_text_view.text.toString()

    private fun showPin(pin: String) {
        pin_value_text_view.text = pin
    }
}
