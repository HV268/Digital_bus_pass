package com.example.digital_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView

class currentpass : AppCompatActivity() {
    companion object{
        const val NAME = "NAME"
        const val AGE = "AGE"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currentpass)

       val name =intent.getStringExtra(NAME)
        val age = intent.getStringExtra(AGE)

        val editText3 = findViewById<TextView>(R.id.editTextText3)
        val editText4 = findViewById<TextView>(R.id.editTextText4)

        editText3.text = name
        editText4.text = age




    }
}