package com.example.calculatorapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private lateinit var previousCalculationTextView: TextView

    private var firstNumber = 0.0
    private var operation = ""
    private var isNewOperation: Boolean = true


    private fun appendNumber(number: String) {
        if (isNewOperation) {
            resultTextView.text = number
            isNewOperation = false
        } else{
            resultTextView.text = "${resultTextView.text}$number"
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        resultTextView = findViewById(R.id.resultTextView)
        previousCalculationTextView = findViewById(R.id.previousCalculationTextView)


        val button0: Button = findViewById(R.id.btnZero)
        val button1: Button = findViewById(R.id.btnOne)
        val button2: Button = findViewById(R.id.btnTwo)
        val button3: Button = findViewById(R.id.btnThree)
        val button4: Button = findViewById(R.id.btnFour)
        val button5: Button = findViewById(R.id.btnFive)
        val button6: Button = findViewById(R.id.btnSix)
        val button7: Button = findViewById(R.id.btnSeven)
        val button8: Button = findViewById(R.id.btnEight)
        val button9: Button = findViewById(R.id.btnNine)
        val buttonDot: Button = findViewById(R.id.btnDot)
        val buttonClr: Button = findViewById(R.id.btnClear)
        val buttonBack: Button = findViewById(R.id.btnBackspace)
        val buttonPercent: Button = findViewById(R.id.btnPercent)
        val buttonDivide: Button = findViewById(R.id.btnDivide)
        val buttonMultiply: Button = findViewById(R.id.btnMultiplication)
        val buttonSub: Button = findViewById(R.id.btnSubtsract)
        val buttonAdd: Button = findViewById(R.id.btnAddition)
        val buttonEqual: Button = findViewById(R.id.btnEqual)

        //append number
        button0.setOnClickListener { appendNumber(number = "0") }
        button1.setOnClickListener { appendNumber(number = "1") }
        button2.setOnClickListener { appendNumber(number = "2") }
        button3.setOnClickListener { appendNumber(number = "3") }
        button4.setOnClickListener { appendNumber(number = "4") }
        button5.setOnClickListener { appendNumber(number = "5") }
        button6.setOnClickListener { appendNumber(number = "6") }
        button7.setOnClickListener { appendNumber(number = "7") }
        button8.setOnClickListener { appendNumber(number = "8") }
        button9.setOnClickListener { appendNumber(number = "9") }
        buttonDot.setOnClickListener { appendNumber(number = ".") }

        // operation
        buttonAdd.setOnClickListener { performOperation(operation = "+") }
        buttonSub.setOnClickListener { performOperation(operation = "-") }
        buttonMultiply.setOnClickListener { performOperation(operation = "*") }
        buttonDivide.setOnClickListener { performOperation(operation = "/") }
        buttonPercent.setOnClickListener { performOperation(operation = "%") }


        buttonEqual.setOnClickListener { calculateResult() }
        buttonClr.setOnClickListener { clearAll() }
        buttonBack.setOnClickListener { backspace() }


    }

    private fun backspace() {
        val length = resultTextView.text.length
        if (length > 0) {
            resultTextView.text = resultTextView.text.subSequence(0, length - 1)
        }
    }

    private fun clearAll() {
        resultTextView.text = "0"
        firstNumber = 0.0
        operation = ""
        isNewOperation = true
        previousCalculationTextView.text = ""
    }

    private fun calculateResult() {
        try {
            val secondNumber : Double = resultTextView.text.toString().toDouble()
            val result: Double = when (operation) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> secondNumber

            }
            resultTextView.text = result.toString()
            previousCalculationTextView.text = ""
            isNewOperation = true
        } catch (e: Exception) {
            resultTextView.text = "Error"
        }
    }

    private fun performOperation(operation: String) {
        firstNumber = resultTextView.text.toString().toDouble()
        this.operation = operation
        isNewOperation = true
        previousCalculationTextView.text = "$firstNumber $operation"
    }
}
