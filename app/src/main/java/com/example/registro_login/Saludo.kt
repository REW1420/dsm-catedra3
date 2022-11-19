package com.example.registro_login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class Saludo : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        val logout_button = findViewById<Button>(R.id.logout_button)

        logout_button.setOnClickListener{
            startActivity(Intent(this, Activity_loginn::class.java))
            Toast.makeText(this, "Sesion cerrada", Toast.LENGTH_LONG).show()
        }


    }
}