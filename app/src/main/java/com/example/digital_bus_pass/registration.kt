package com.example.digital_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.digital_bus_pass.databinding.ActivityRegistrationBinding
import com.google.firebase.auth.FirebaseAuth

class registration : AppCompatActivity() {
   private lateinit var binding:ActivityRegistrationBinding
   private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseAuth=FirebaseAuth.getInstance()
        binding.textView6.setOnClickListener {
            val intent =Intent(this ,MainActivity2::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener{
            val email=binding.emailinput.text.toString()
            val pass=binding.pass1.text.toString()
            val confpass=binding.confpass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty() && confpass.isNotEmpty()){
                if(pass == confpass){
                    firebaseAuth.createUserWithEmailAndPassword(email , pass).addOnCompleteListener{
                        if(it.isSuccessful){
                            val intent =Intent(this ,MainActivity2::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"Password is not Matching",Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this,"Empty fields are not allowed!!",Toast.LENGTH_SHORT).show()
            }
        }
    }


}