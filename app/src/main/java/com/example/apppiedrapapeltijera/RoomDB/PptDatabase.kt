package com.example.apppiedrapapeltijera.RoomDB

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apppiedrapapeltijera.RoomDB.PPT
import com.example.apppiedrapapeltijera.RoomDB.PPTDao


@Database(entities = arrayOf(PPT::class), version = 1)
abstract class PptDatabase:RoomDatabase() {
    abstract fun PPTDao() : PPTDao
}