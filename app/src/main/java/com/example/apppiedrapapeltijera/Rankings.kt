package com.example.apppiedrapapeltijera


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.apppiedrapapeltijera.FirebaseDatabase.JugadorEntity
import com.example.apppiedrapapeltijera.FirebaseDatabase.MetodosDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class Rankings : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView
    lateinit var adapter: JugadorAdapter
    lateinit var tasks: MutableList<JugadorEntity>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rankings)
        tasks = ArrayList()         // Se prepara la lista
        getTasks()                  // Se carga la lista de tareas a través del DAO
    }
    fun setUpRecyclerView(tasks: List<JugadorEntity>) {    // Método que muestra la vista usando el adaptador
        adapter = JugadorAdapter(tasks)
        recyclerView = findViewById(R.id.rvTask)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }
    fun getTasks()= runBlocking {       // Corrutina que saca de la base de datos la lista de tareas
        launch { // Inicio del hilo
            var db = FirebaseFirestore.getInstance()
            db.collection("Jugadores").get().addOnSuccessListener { documents ->
                for (document in documents) {
                    var jugadorNuevo = JugadorEntity()
                    jugadorNuevo.username = document.getString("username").toString()
                    jugadorNuevo.distanciaMaxima = document.getLong("distanciaMaxima")!!.toInt()
                    jugadorNuevo.partidasJugadas = document.getLong("partidasJugadas")!!.toInt()
                    tasks.add(jugadorNuevo)
                }
                setUpRecyclerView(tasks)
            } // Se carga la lista de tareas
        }
    }
}
