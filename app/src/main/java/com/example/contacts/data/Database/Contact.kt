package com.example.contacts.data.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "contact_table")

data class Contact (
   @PrimaryKey(autoGenerate = true)   var id : Int = 0,
   @ColumnInfo(name = "user name")   var name : String,
       var number: String,
       var email: String,
       var dateOfCreation: Long,
       var isActive: Boolean,
       var image: ByteArray  ?= null
   )