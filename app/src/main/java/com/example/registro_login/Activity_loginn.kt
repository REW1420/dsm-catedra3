package com.example.registro_login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Activity_loginn : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")

    lateinit var registroDB: sqlHelper

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //obejto
        registroDB = sqlHelper(this, "registro", null, 1)
        val db = registroDB.readableDatabase

        val login_button = findViewById<Button>(R.id.login_button)
        val login_email = findViewById<TextView>(R.id.login_email)
        val login_password = findViewById<TextView>(R.id.login_password)









        login_button.setOnClickListener {
            val args = listOf<String>(
                login_email.text.toString(),
                login_password.text.toString()
            ).toTypedArray()
            val req =
                db.rawQuery("SELECT * FROM registro WHERE correo = ? AND contrasena = ?", args)


            if (login_email.text.isNotBlank() && login_password.text.isNotBlank()) {


                if (login_email.text.isNotBlank() || login_password.text.isNotBlank()) {
                    if (req.moveToNext()) {
                        Toast.makeText(this, "Bienvenido", Toast.LENGTH_LONG).show()
                        startActivity(Intent(this,com.example.registro_login.Saludo::class.java))
                    }
                    else {
                        Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Rellene todos los datos", Toast.LENGTH_LONG).show()
                }


            } else {
                Toast.makeText(this, "Rellene todos los datos", Toast.LENGTH_LONG).show()
            }
        }


        //enrutamiento
        val signupRedirectText = findViewById<TextView>(R.id.signupRedirectText)
        signupRedirectText.setOnClickListener {
            startActivity(Intent(this, com.example.registro_login.activity_sign_up::class.java))
        }
    }
}