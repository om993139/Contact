package com.example.contacts.ui_layer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.contacts.data.Database.Contact

data class ContactState (
     val id: MutableState<Int> = mutableStateOf(0),
     val contact : List<Contact> = emptyList(),
     val name : MutableState<String> = mutableStateOf(""),
     val number : MutableState<String> = mutableStateOf(""),
     val email : MutableState<String> = mutableStateOf(""),
     val dateOfCreation : MutableState<Long> = mutableStateOf(0),
     val image: MutableState<ByteArray> = mutableStateOf(ByteArray(0))



 )