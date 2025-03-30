package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var currentInput = ""
    private var operator = ""
    private var firstOperand = ""


    private fun updateDisplay() {
        tvDisplay.text = currentInput.ifEmpty { "0" }
    }


    private fun appendNumber(number: String) {
        if (currentInput == "0") {
            currentInput = number  // Thay thế số 0 ban đầu
        } else {
            currentInput += number
        }
        updateDisplay()
    }

    private fun appendDecimal() {
        if (!currentInput.contains(".")) {
            currentInput += "."
            updateDisplay()
        }
    }

    private fun setOperator(op: String) {
        if (currentInput.isNotEmpty()) {
            firstOperand = currentInput
            operator = op
            currentInput = ""
            updateDisplay()
        }
    }

    private fun calculateResult() {
        if (firstOperand.isNotEmpty() && operator.isNotEmpty() && currentInput.isNotEmpty()) {
            val num1 = firstOperand.toDouble()
            val num2 = currentInput.toDouble()
            val result = when (operator) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else "Lỗi"
                else -> "Lỗi"
            }
            currentInput = result.toString()
            firstOperand = ""
            operator = ""
            updateDisplay()
        }
    }


    private fun clearAll() {
        currentInput = ""
        firstOperand = ""
        operator = ""
        updateDisplay()
    }

    private fun deleteLastCharacter() {
        if (currentInput.isNotEmpty()) {
            currentInput = currentInput.dropLast(1)
            updateDisplay()
        }
    }

    private fun toggleSign() {
        if (currentInput.isNotEmpty() && currentInput != "0") {
            currentInput = if (currentInput.startsWith("-")) {
                currentInput.substring(1)  // Bỏ dấu -
            } else {
                "-$currentInput"  // Thêm dấu -
            }
            updateDisplay()
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val numberButtons = listOf(
            findViewById(R.id.btn0),
            findViewById<Button>(R.id.btn1),
            findViewById<Button>(R.id.btn2),
            findViewById<Button>(R.id.btn3),
            findViewById<Button>(R.id.btn4),
            findViewById<Button>(R.id.btn5),
            findViewById<Button>(R.id.btn6),
            findViewById<Button>(R.id.btn7),
            findViewById<Button>(R.id.btn8),
            findViewById<Button>(R.id.btn9)
        )



        numberButtons.forEach { button ->
            button.setOnClickListener {
                appendNumber(button.text.toString())
            }
        }



        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }


        findViewById<Button>(R.id.btnEqual).setOnClickListener { calculateResult() }


        findViewById<Button>(R.id.btnDelete).setOnClickListener { deleteLastCharacter() }


        findViewById<Button>(R.id.btncomma).setOnClickListener { appendDecimal() }
        findViewById<Button>(R.id.btnClear).setOnClickListener { clearAll() }
        findViewById<Button>(R.id.btnToggleSign).setOnClickListener { toggleSign() }




    }
}