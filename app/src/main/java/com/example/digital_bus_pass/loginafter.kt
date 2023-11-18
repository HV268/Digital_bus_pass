package com.example.digital_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class loginafter : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loginafter)
    }

    fun back(view: View) {
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
    }

    fun newpass(view: View) {
        val intent = Intent(this,newpass::class.java)
        startActivity(intent)
    }

    fun cureentpass(view: View) {
        val intent = Intent(this ,currentpass::class.java)
        startActivity(intent)
    }
}