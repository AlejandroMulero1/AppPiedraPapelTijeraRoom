package com.example.apppiedrapapeltijera.FirebaseDatabase

import android.content.ContentValues.TAG
import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class MetodosDatabase {
    val db = FirebaseFirestore.getInstance()
    var jugador: JugadorEntity = JugadorEntity("", 0, 0)
    var listaJugadores: MutableList<JugadorEntity> = mutableListOf<JugadorEntity>()

    //Función que recibe un nickname e inserta en la BBDD dicho jugador
    fun Insertar(nickname: String) {
        val jugadorExiste=CargarJugador(nickname);
        if (jugadorExiste!=null){
            val jugador = JugadorEntity(nickname, 0, 0)
            db.collection("Jugadores").document(nickname).set(jugador)
        }
    }

    //Método que recibe un username y busca recoge de la BBDD dicho jugador convirtiendolo en objeto
    //de tipo JugadorEntity
    fun CargarJugador(username: String){
        val jugadorRef = db.collection("Jugadores").document(username)

        jugadorRef.get().addOnSuccessListener { documentSnapshot ->
            Log.d(TAG, "hola" + documentSnapshot)
            if(documentSnapshot.exists()){
                jugador.username = documentSnapshot.getString("username").toString()
                Log.d(TAG, "hola"+jugador.username)
                jugador.distanciaMaxima = documentSnapshot.getLong("distanciaMaxima")!!.toInt();
                jugador.partidasJugadas = documentSnapshot.getLong("partidasJugadas")!!.toInt();
            }
        }
    }

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

    //Función que recibe un username y distancia máxima y actualiza al jugador cargado en la llamada
    //al método que carga jugadores su distanciaMáxima
    fun actualizarDistanciaMaxima(username: String, distanciaMaxima: Int){
        if (jugador.distanciaMaxima!!<distanciaMaxima){
            jugador.distanciaMaxima = distanciaMaxima
        }
        db.collection("Jugadores").document(username).set(jugador)
    }

    //Función que recibe un username y actualiza al jugador cargado sus partidas jugadas incrementándolo en 1
    fun actualizarPartidasJugadas(username: String){
        jugador.partidasJugadas = jugador.partidasJugadas?.plus(1)
        db.collection("Jugadores").document(username).set(jugador)
    }
}

