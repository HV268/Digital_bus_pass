package com.example.digital_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class forgot : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot)
    }

    fun backButton(view: View) {
        val intent = Intent(this , MainActivity2::class.java)
        startActivity(intent)
    }
}