package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var tvResult:TextView
    var lastNumeric:Boolean = false
    var lastDot:Boolean = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvResult= findViewById(R.id.result_tv)


    }

    fun onDigit(view: View) {
        tvResult.append((view as Button).text)
        lastNumeric = true
        lastDot = false
    }

    fun onClear(view: View) {
        tvResult.text = ""
    }

    fun onDecimal(view: View){
        if (lastNumeric && !lastDot){
            tvResult.append(".")
            lastDot = true
            lastNumeric = false
        }
    }
    fun onOperator(view: View){
        tvResult.text.let {
            if (lastNumeric && !isOperatorAdded(it.toString())){
                tvResult.append((view as Button).text)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun isOperatorAdded(value: String) : Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("÷") || value.contains("×") || value.contains("+") || value.contains("-")
        }
    }
    fun onEqual(view: View){
        if (lastNumeric){
            var tvValue = tvResult.text.toString()
            var prefix = ""
            try {
                if (tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if (tvValue.contains("-")) {
                    val splitValue = tvValue.split("-")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult.text = (one.toDouble() - two.toDouble()).toString()
                }else if (tvValue.contains("+")) {
                    val splitValue = tvValue.split("+")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult.text = (one.toDouble() + two.toDouble()).toString()
                }else if (tvValue.contains("x")) {
                    val splitValue = tvValue.split("x")
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult.text = (one.toDouble() * two.toDouble()).toString()
                }else if (tvValue.contains(resources.getString(R.string.divide_sign))) {
                    val splitValue = tvValue.split(resources.getString(R.string.divide_sign))
                    var one = splitValue[0]
                    var two = splitValue[1]
                    if (prefix.isNotEmpty()){
                        one = prefix + one
                    }
                    tvResult.text = (one.toDouble() / two.toDouble()).toString()
                }
            }catch (ex:ArithmeticException){
                ex.printStackTrace()
            }
        }
    }
}