package com.example.apppiedrapapeltijera

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val etNombreUsuario=findViewById<EditText>(R.id.etNick)
        findViewById<Button>(R.id.btnIniciar).setOnClickListener{
            GlobalScope.launch {
               val jugadorInsertar= PPT(etNombreUsuario.text.toString(), 0 , 0)
                PptApp.database.PPTDao().insertar(jugadorInsertar);
            }

        }
    }
}