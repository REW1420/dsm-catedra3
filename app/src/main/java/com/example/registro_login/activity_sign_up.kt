package com.example.registro_login

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class activity_sign_up : AppCompatActivity() {

    lateinit var registroDB: sqlHelper
    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        //objeto de sql
        registroDB = sqlHelper(this, "registro", null, 1)

        val loginRedirectText = findViewById<TextView>(R.id.loginRedirectText)
        val signup_button = findViewById<Button>(R.id.logout_button)
        val signup_email = findViewById<TextView>(R.id.signup_email)
        val sigup_user = findViewById<TextView>(R.id.signup_user)
        val sigup_password = findViewById<TextView>(R.id.signup_password)
        val sigup_pasword2 = findViewById<TextView>(R.id.signup_password2)
        val txtErrorPasword = findViewById<TextView>(R.id.txtErrorPassword)
        val txtErrorPassword2 = findViewById<TextView>(R.id.txtErrorPassword2)
        val txtErrorEmail = findViewById<TextView>(R.id.txtErrorEmail)



        val patternEmail = Regex("^[^@]+@[^@]+\\.[a-zA-Z]{2,}\$");
        val patternPassword = Regex("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{4,8}\$")

        //enrutamiento

        loginRedirectText.setOnClickListener {
            startActivity(Intent(this, com.example.registro_login.Activity_loginn::class.java))
        }

        //evento principal
        signup_button.setOnClickListener {
            val password1 = sigup_password.text.toString()
            val password2 = sigup_pasword2.text.toString()

            txtErrorPasword.text=""
            txtErrorPassword2.text=""
            txtErrorEmail.text=""

            val resultEmail = patternEmail.containsMatchIn(signup_email.text.toString())
            val resultPassword = patternPassword.containsMatchIn(sigup_password.text.toString())

            if (sigup_user.text.isBlank() && signup_email.text.isBlank() && sigup_password.text.isBlank() && sigup_pasword2.text.isBlank()) {
                Toast.makeText(this, "Llene los campos por favor", Toast.LENGTH_LONG).show()
            } else if (sigup_user.text.isBlank() || signup_email.text.isBlank() || sigup_password.text.isBlank() || sigup_pasword2.text.isBlank()) {
                Toast.makeText(this, "Aun quedan campos vacios", Toast.LENGTH_LONG).show()
            } else if (resultEmail) {
               if (resultPassword){
                   if (password1 == password2) {
                       registroDB.add(
                           sigup_user.text.toString(),
                           signup_email.text.toString(),
                           sigup_pasword2.text.toString()
                       )
                       startActivity(
                           Intent(
                               this,
                               com.example.registro_login.Activity_loginn::class.java
                           )
                       )
                       Toast.makeText(this, "Registrado", Toast.LENGTH_LONG).show()

                   } else {
                       txtErrorPassword2.text= "Las contraseñas no coinciden"
                   }
               } else {
                   txtErrorPasword.text = "La contraseña debe tener 4 a 8 caracteres, minusculas y mayusculas"
               }
            } else {
                txtErrorEmail.text= "Ingrese un correo valido"
            }
        }

//corchetes finales
    }

}