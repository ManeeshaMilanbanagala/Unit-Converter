package com.example.mobileapplication

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var inputValue: EditText
    private lateinit var convertButton: Button
    private lateinit var resultText: TextView
    private lateinit var conversionGroup: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputValue = findViewById(R.id.inputValue)
        convertButton = findViewById(R.id.convertButton)
        resultText = findViewById(R.id.resultText)
        conversionGroup = findViewById(R.id.conversionGroup)

        convertButton.setOnClickListener {
            performConversion()
        }
    }

    private fun performConversion() {
        val input = inputValue.text.toString()
        if (input.isEmpty()) {
            Toast.makeText(this, "Please enter a value", Toast.LENGTH_SHORT).show()
            return
        }

        val value = input.toDoubleOrNull()
        if (value == null) {
            Toast.makeText(this, "Please enter a valid number", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedConversionId = conversionGroup.checkedRadioButtonId
        if (selectedConversionId == -1) {
            Toast.makeText(this, "Please select a conversion type", Toast.LENGTH_SHORT).show()
            return
        }

        val result = when (selectedConversionId) {
            R.id.radioKmToM -> {
                val converted = value * 1000
                "$value kilometers = $converted meters"
            }
            R.id.radioFToC -> {
                val converted = (value - 32) * 5 / 9
                "$value°F = ${String.format("%.2f", converted)}°C"
            }
            R.id.radioGToKg -> {
                val converted = value / 1000
                "$value grams = $converted kilograms"
            }
            else -> ""
        }

        resultText.text = result
    }
}
