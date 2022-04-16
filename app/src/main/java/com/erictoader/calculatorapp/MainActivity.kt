package com.erictoader.calculatorapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var tvInput: TextView? = null
    var number1 = 0.0
    var number2 = 0.0
    var result = 0.0
    var operation = ""
    var dot = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.result)
    }

    fun onDigit(view: View) {
        if(this.result != 0.0) {
            this.result = 0.0
            onClear(view)
        }

        if(tvInput?.text?.startsWith('0', false)!! && !this.dot) {
            tvInput?.text = (view as Button).text
        } else {
            tvInput?.append((view as Button).text)
        }
    }

    fun onDot(view: View) {
        if(!this.dot && !tvInput?.text.toString().contains('.', false)) {
            this.dot = true
            tvInput?.append((view as Button).text)
        }
    }

    fun onOperation(view: View) {
        this.number1 = tvInput?.text.toString().toDouble()
        this.operation = (view as Button).text.toString()
        onClear(view)
    }

    fun onClear(view: View) {
        tvInput?.text = "0"
        this.dot = false
    }

    fun onEqual(view: View) {
        this.number2 = tvInput?.text.toString().toDouble()

        if(this.operation == "/") {
            if(this.number2 != 0.0) {
                this.result = number1 / number2
            } else {
                Toast.makeText(this,
                "Cannot divide by zero", Toast.LENGTH_SHORT).show()
                onClear(view)
            }
        }
        else if(this.operation == "*") {
            this.result = number1 * number2
        }
        else if(this.operation == "-") {
            this.result = number1 - number2
        }
        else if(this.operation == "+") {
            this.result = number1 + number2
        }
        else {
            this.result = tvInput?.text.toString().toDouble()
        }

        tvInput?.text = this.result.toString()
        this.number1 = 0.0
        this.number2 = 0.0
        this.dot = false
        this.operation = ""
    }

}