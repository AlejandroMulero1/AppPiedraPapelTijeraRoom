package com.example.apppiedrapapeltijera

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(PPT::class), version = 1)
abstract class PptDatabase:RoomDatabase() {
    abstract fun PPTDao() : PPTDao
}