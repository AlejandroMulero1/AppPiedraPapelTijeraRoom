package com.example.apppiedrapapeltijera.FirebaseDatabase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class JugadorEntity(var username: String="", var distanciaMaxima: Int=0, var partidasJugadas: Int=0) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}