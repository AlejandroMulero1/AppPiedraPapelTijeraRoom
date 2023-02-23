package com.example.apppiedrapapeltijera.FirebaseDatabase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class JugadorEntity(var username: String? = null, var distanciaMaxima: Int? = null, var partidasJugadas: Int? = null) {
    // Null default values create a no-argument default constructor, which is needed
    // for deserialization from a DataSnapshot.
}