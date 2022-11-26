package com.example.apppiedrapapeltijera

import androidx.room.*

@Dao
interface PPTDao {
    @Query("SELECT * FROM ppt_table ORDER BY distancia_maxima")
    suspend fun obtenerTodo(): List<PPT>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertar(datosJugador: PPT)

    @Query("UPDATE ppt_table SET partidas_jugadas=(partidas_jugadas+1) WHERE nick=:nombreUsuario")
    suspend fun aumentarPartidasJugadas(nombreUsuario: String)
}