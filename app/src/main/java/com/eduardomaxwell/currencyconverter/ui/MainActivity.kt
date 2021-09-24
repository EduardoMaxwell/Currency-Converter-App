package com.eduardomaxwell.currencyconverter.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.eduardomaxwell.currencyconverter.R
import com.eduardomaxwell.currencyconverter.databinding.ActivityMainBinding

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

        binding.txtResult.text = value
        binding.txtResult.visibility = View.VISIBLE

    }
}