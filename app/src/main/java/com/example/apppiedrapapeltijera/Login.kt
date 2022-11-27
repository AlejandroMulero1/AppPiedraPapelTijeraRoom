package com.example.apppiedrapapeltijera

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    override fun onStart() {
        super.onStart()
        val etNombreUsuario=findViewById<EditText>(R.id.etNick)
        findViewById<Button>(R.id.btnIniciar).setOnClickListener{
            GlobalScope.launch {
                val jugadorInsertar= PPT(etNombreUsuario.text.toString(), 0 , 0)
                PptApp.database.PPTDao().insertar(jugadorInsertar);
            }
            val etNombreUsuario = findViewById<EditText>(R.id.etNick)
            val i = Intent(this, MainActivity::class.java)
            i.putExtra("nombreUsuario", etNombreUsuario.text.toString())
            startActivity(i)
        }
        }
    }
