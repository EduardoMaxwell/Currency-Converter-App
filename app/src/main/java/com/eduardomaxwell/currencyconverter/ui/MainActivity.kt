package com.eduardomaxwell.currencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eduardomaxwell.currencyconverter.R
import com.eduardomaxwell.currencyconverter.databinding.ActivityMainBinding
import org.json.JSONObject
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnConverter.setOnClickListener {
            this.converter()
        }
    }

    private fun converter() {
        val selectedCurrency = binding.radioGroup

        val currency = when (selectedCurrency.checkedRadioButtonId) {
            R.id.radioUSD -> "USD"
            R.id.radioEUR -> "EUR"
            else -> "CLP"
        }

        val value = binding.editCurrencyValue.text.toString()

        if (value.isEmpty())
            return

        Thread {
            val url =
                URL("https://free.currconv.com/api/v7/convert?q=${currency}_BRL&compact=ultra&apiKey=f847912793edfae40708")

            val conn = url.openConnection() as HttpsURLConnection

            try {
                val data = conn.inputStream.bufferedReader().readText()

                val obj = JSONObject(data)


                runOnUiThread {
                    val res = obj.getDouble("${currency}_BRL")
                    binding.txtResult.text = "R$${"%.2f".format(value.toDouble() * res)}"
                    binding.txtResult.visibility = View.VISIBLE
                }
            } finally {
                conn.disconnect()
            }
        }.start()

    }
}