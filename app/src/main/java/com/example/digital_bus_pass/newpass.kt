package com.example.digital_bus_pass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.view.SurfaceControl.Transaction
import android.widget.Button

import com.phonepe.intent.sdk.api.B2BPGRequestBuilder
import com.phonepe.intent.sdk.api.PhonePe


import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import org.json.JSONObject
import java.nio.charset.Charset
import java.security.MessageDigest

class newpass : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newpass)

        val button = findViewById<Button>(R.id.button4)

        PhonePe.init(this)
        val data = JSONObject()
        data.put("merchantId", "PGTESTPAYUAT")
        data.put("merchantTransactionId", System.currentTimeMillis().toString())
        data.put("merchantUserId", System.currentTimeMillis().toString())
        data.put("amount", 10000)
        data.put("mobileNumber", "7908834635")
        data.put("callbackUrl", "https://webhook.site/0a09e1b8-5676-46f1-91b9-659bb320cfe4")
        val mPaymentInstrumentation = JSONObject()
        mPaymentInstrumentation.put("type", "PAY_PAGE")
        data.put("paymentInstrument", mPaymentInstrumentation)

        val base64Body: String = Base64.encodeToString(
            data.toString().toByteArray(
                Charset.defaultCharset()
            ), Base64.NO_WRAP
        )


        val checksum =
            sha256(base64Body + "/pg/v1/pay" + "099eb0cd-02cf-4e2a-8aca-3e6c6aff0399") + "###1"
        val b2BPGRequest = B2BPGRequestBuilder()
            .setData(base64Body)
            .setChecksum(checksum)
            .setUrl("/pg/v1/pay")
            .build()

        button.setOnClickListener {
            //For SDK call below function
            try {
                startActivityForResult(
                    PhonePe.getImplicitIntent(
                        this, b2BPGRequest, ""
                    )!!, 1
                );
            } catch (e: Exception) {
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1) {
            Toast.makeText(this, "check callback URL", Toast.LENGTH_SHORT).show()

        }
        else{

            val editText = findViewById<EditText>(R.id.editTextTextPersonName)
            val editText2= findViewById<EditText>(R.id.editTextTextPersonName2)
            val name = editText.text.toString()
            val age = editText2.text.toString()
            val intent = Intent(this@newpass,currentpass::class.java)
               intent.putExtra(currentpass.NAME,name)
               intent.putExtra(currentpass.AGE,age)
               startActivity(intent)
        }

    }


    private fun sha256(input: String): String {
        val byte = input.toByteArray(Charsets.UTF_8)
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(byte)
        return digest.fold("") { str, it -> str + "%02x".format(it)



}

        fun back3(view: View) {
            val intent = Intent(this,loginafter::class.java)
            startActivity(intent)
        }

    }
}









