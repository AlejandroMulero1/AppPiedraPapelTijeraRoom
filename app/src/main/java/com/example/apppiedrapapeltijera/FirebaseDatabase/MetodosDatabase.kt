package com.example.apppiedrapapeltijera.FirebaseDatabase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MetodosDatabase {
    val db = FirebaseFirestore.getInstance()
    var jugador: JugadorEntity = JugadorEntity()
    var listaJugadores: MutableList<JugadorEntity> = mutableListOf<JugadorEntity>()


    //función que llama a la BBDD y carga la lista de documentos de la tabla jugadores
    //y en la que se convierte cada documento en objeto de tipo JugadorEntity para
    //almacenarlos en una lista mutable de objetos de tipo JugadorEntity
    fun ObtenerLista(){
        listaJugadores = mutableListOf<JugadorEntity>()
        db.collection("Jugadores").get().addOnSuccessListener { documents ->
            for (document in documents) {
                var jugadorNuevo = JugadorEntity()
                jugadorNuevo.username = document.getString("username").toString()
                Log.d(TAG, "hola"+jugador.username)
                jugadorNuevo.distanciaMaxima = document.getLong("distanciaMaxima")!!.toInt()
                jugadorNuevo.partidasJugadas = document.getLong("partidasJugadas")!!.toInt()
                listaJugadores.add(jugadorNuevo)
            }
        }
    }
}

