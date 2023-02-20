package com.example.apppiedrapapeltijera.RoomDB

import androidx.room.*
import com.example.apppiedrapapeltijera.RoomDB.PPT

@Dao
interface PPTDao {
    @Query("SELECT * FROM ppt_table ORDER BY distancia_maxima DESC")
    suspend fun obtenerTodo(): MutableList<PPT>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(datosJugador: PPT)

    @Query("UPDATE ppt_table SET partidas_jugadas=(partidas_jugadas+1) WHERE nick=:nombreUsuario")
    suspend fun aumentarPartidasJugadas(nombreUsuario: String)

    @Query("UPDATE ppt_table SET distancia_maxima=:distanciaMaxima WHERE nick=:nombreUsuario")
    suspend fun actualizarDistanciaMaxima(nombreUsuario: String, distanciaMaxima: Int)

    @Query ("SELECT distancia_maxima FROM ppt_table WHERE nick=:nombreUsuario")
    suspend fun obtenerDistanciaMaxima(nombreUsuario: String) : Int
}