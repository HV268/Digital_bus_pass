package com.example.digital_bus_pass

import android.content.Intent
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.digital_bus_pass.databinding.ActivityMain2Binding
import com.google.firebase.auth.FirebaseAuth
import java.util.regex.Pattern

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        binding.newReg1.setOnClickListener {
            val intent = Intent(this, registration::class.java)
            startActivity(intent)
        }
        binding.button.setOnClickListener {
            val email = binding.emailinput.text.toString()
            val pass = binding.pass1.text.toString()


            if (email.isNotEmpty() && pass.isNotEmpty()) {

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, loginafter::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }

            } else {
                Toast.makeText(this, "Empty fields are not allowed!!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.textView3.setOnClickListener {
            val builder =AlertDialog.Builder(this)
            val view =layoutInflater.inflate(R.layout.activity_forgot,null)
            val userEmail=view.findViewById<EditText>(R.id.editbox)

            builder.setView(view)
            val dialog =builder.create()

            view.findViewById<Button>(R.id.btnreset).setOnClickListener{
               compareEmail(userEmail)
                dialog.dismiss()
            }
            view.findViewById<Button>(R.id.button3).setOnClickListener{
                dialog.dismiss()
            }
            if(dialog.window !=null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()
        }

    }
    private  fun compareEmail(email: EditText){
        if(email.text.toString().isEmpty()){
            return
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()){
            return
        }
        firebaseAuth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener{task ->
            if(task.isSuccessful){
                Toast.makeText(this,"Check Your Email",Toast.LENGTH_SHORT).show()
            }
        }

    }
}