package com.example.currency

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(){

    private lateinit var spinnerFromCurrency: Spinner
    private lateinit var spinnerToCurrency: Spinner
    private lateinit var editTextAmountFrom: EditText
    private lateinit var editTextAmountTo: EditText
    private lateinit var textViewResult: TextView
    private lateinit var currencySymbolFrom: TextView
    private lateinit var currencySymbolTo: TextView

    private val exchangeRates = mapOf(
        "United States - Dollar" to 1.0,
        "VietNam - Dong" to 23000.0,
        "Europe - Euro" to 0.85,
        "Japan - Yen" to 135.0,
        "British - Pound" to 0.81

    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerFromCurrency = findViewById(R.id.spinnerFromCurrency)
        spinnerToCurrency = findViewById(R.id.spinnerToCurrency)
        editTextAmountFrom =  findViewById(R.id.editTextAmountFrom)
        editTextAmountTo = findViewById(R.id.editTextAmountTo)
        textViewResult = findViewById(R.id.textViewResult)
        currencySymbolFrom = findViewById(R.id.currencySymbolFrom)
        currencySymbolTo = findViewById(R.id.currencySymbolTo)



        val currencylist = resources.getStringArray(R.array.currency_list)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencylist)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerFromCurrency.adapter = adapter
        spinnerToCurrency.adapter = adapter

        setupListener()

    }

    private fun setupListener() {
        spinnerFromCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateConvertedAmount()
//                updateCurrencySymbols()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }

        spinnerToCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parentView: AdapterView<*>?, view: View?, position: Int, id: Long) {
                updateConvertedAmount()
//                updateCurrencySymbols()
            }

            override fun onNothingSelected(parentView: AdapterView<*>?) {}
        }


        editTextAmountFrom.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(editable: Editable?) {
                updateConvertedAmount()

            }
        })






    }
    private fun updateConvertedAmount() {
        val fromCurrency = spinnerFromCurrency.selectedItem.toString()
        val toCurrency = spinnerToCurrency.selectedItem.toString()

        val symbolFrom = when (fromCurrency) {
            "United States - Dollar" -> "$"
            "VietNam - Dong" -> "₫"
            "Europe - Euro" -> "€"
            "Japan - Yen" -> "¥"
            "British - Pound" -> "£"
            else -> ""
        }
        val symbolTo = when (toCurrency) {
            "United States - Dollar" -> "$"
            "VietNam - Dong" -> "₫"
            "Europe - Euro" -> "€"
            "Japan - Yen" -> "¥"
            "British - Pound" -> "£"
            else -> ""
        }
        currencySymbolFrom.text = symbolFrom
        currencySymbolTo.text = symbolTo

        val amountFrom = editTextAmountFrom.text.toString().toDoubleOrNull() ?: return

        val fromRate = exchangeRates[fromCurrency] ?: return
        val toRate = exchangeRates[toCurrency] ?: return

        val convertedAmount = amountFrom * (toRate / fromRate)
        val formattedAmount = String.format("%.2f", convertedAmount)
        editTextAmountTo.setText(formattedAmount)

        val resultText = "$amountFrom $symbolFrom = $formattedAmount $symbolTo"
        textViewResult.text = resultText



    }

    private fun updateCurrencySymbols() {
        val fromCurrency = spinnerFromCurrency.selectedItem.toString()
        val toCurrency = spinnerToCurrency.selectedItem.toString()


        val symbolFrom = when (fromCurrency) {
            "United States - Dollar" -> "$"
            "VietNam - Dong" -> "₫"
            "Europe - Euro" -> "€"
            else -> ""
        }


        val symbolTo = when (toCurrency) {
            "United States - Dollar" -> "$"
            "VietNam - Dong" -> "₫"
            "Europe - Euro" -> "€"
            else -> ""
        }

        currencySymbolFrom.text = symbolFrom
        currencySymbolTo.text = symbolTo
    }






}

