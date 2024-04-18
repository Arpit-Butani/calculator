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
            if (lastNumeric && isOperatorAdded(it.toString())){
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
            value.contains("÷")||value.contains("×")||value.contains("+")||value.contains("-")
        }
    }
}