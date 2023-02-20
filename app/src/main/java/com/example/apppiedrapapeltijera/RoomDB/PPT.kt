package com.example.apppiedrapapeltijera.RoomDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ppt_table")
data class PPT(
    @PrimaryKey val nick:String,
    @ColumnInfo(name = "partidas_jugadas") val partidasJugadas: Int=0,
    @ColumnInfo(name = "distancia_maxima") val distanciaMaxima: Int=0
)
