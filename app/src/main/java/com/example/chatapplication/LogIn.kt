package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

class LogIn : AppCompatActivity() {

    private lateinit var edtEmail : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnLogIn : Button
    private lateinit var btnSignUp : Button

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in)

        supportActionBar?.hide()

        mAuth = FirebaseAuth.getInstance()

        edtEmail = findViewById(R.id.edt_email)
        edtPassword = findViewById(R.id.edt_password)
        btnLogIn = findViewById(R.id.btnLogIn)
        btnSignUp = findViewById(R.id.btnSingnUp)

        btnSignUp.setOnClickListener{
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)

            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
            //connexion/authentification
    //https://firebase.google.com/docs/auth/android/password-auth#kotlin+ktx_3
    // Lorsqu'un utilisateur se connecte à votre application, passer l'adresse e - mail de l'utilisateur et mot de passe
    // signInWithEmailAndPassword :
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    //code for loging in user / code pour ce connecté en tant qu'utilisateur
                    val intent = Intent(this@LogIn,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If log in fails, display a message to the user.
                  Toast.makeText(this@LogIn,"l'utilisateur n'existe pas", Toast.LENGTH_LONG).show()
                }
            }

    }
}