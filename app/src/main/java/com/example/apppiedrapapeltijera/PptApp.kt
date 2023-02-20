package com.example.apppiedrapapeltijera

import android.app.Application
import androidx.room.Room
import com.example.apppiedrapapeltijera.RoomDB.PptDatabase

class PptApp: Application() {
    companion object {
        lateinit var database: PptDatabase
    }
    override fun onCreate() {
        super.onCreate()
        database =  Room.databaseBuilder(this, PptDatabase::class.java, "ppt-db").build()
    }
}