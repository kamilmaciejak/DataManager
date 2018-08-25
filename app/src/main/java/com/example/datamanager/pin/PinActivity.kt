package com.example.datamanager.pin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import com.example.datamanager.PIN_OK
import com.example.datamanager.PREFERENCES
import com.example.datamanager.PREFERENCES_PIN
import com.example.datamanager.R
import kotlinx.android.synthetic.main.pin.*

class PinActivity : AppCompatActivity() {

    companion object {
        const val PIN_LENGTH = 4
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        setResult(false)
        super.onBackPressed()
    }

    private fun setResult(pinOk: Boolean) {
        if (callingActivity != null) {
            val intent = Intent().apply {
                putExtra(PIN_OK, pinOk)
            }
            setResult(RESULT_OK, intent)
        }
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

    fun confirmPin(view: View) {
        val sharedPreferences = getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
        sharedPreferences.getString(PREFERENCES_PIN, "").let { pin ->
            setResult(pin.equals(getPin()))
            finish()
        }
    }

    private fun getPin(): String = pin_value_text_view.text.toString()

    private fun showPin(pin: String) {
        pin_value_text_view.text = pin
    }
}
