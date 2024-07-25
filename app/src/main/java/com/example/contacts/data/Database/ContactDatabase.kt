package com.example.contacts.data.Database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Contact::class] , version = 2 , exportSchema = true)
abstract class ContactDatabase : RoomDatabase() {
    abstract val dao: Dao
}