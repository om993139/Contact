package com.example.contacts.ui_layer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contacts.data.Database.Contact
import com.example.contacts.data.Database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ContactViewModel @Inject constructor(var database: ContactDatabase) : ViewModel() {



    private var isSortedByName = MutableStateFlow(true)

    var isSplashScreen by mutableStateOf(false)

  init {
      viewModelScope.launch {
          delay(4000)
          isSplashScreen = false
      }
  }


    @OptIn(ExperimentalCoroutinesApi::class)
    private val contact = isSortedByName.flatMapLatest {
        if (it) {
            database.dao.getContactsSortName()
        } else {
            database.dao.getContactsSortDate()
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val _state = MutableStateFlow(ContactState())

    val state = combine(_state, contact, isSortedByName) { _state, contact, isSortedByName ->

        _state.copy(contact = contact)


    }.stateIn(viewModelScope , SharingStarted.WhileSubscribed(3000), ContactState())


    fun changeSorting(){
        isSortedByName.value = !isSortedByName.value
    }




    fun saveContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            dateOfCreation = System.currentTimeMillis().toLong(),
            isActive = true,
            image =state.value.image.value
        )
        viewModelScope.launch {
            database.dao.upsertContact(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = ByteArray(0)

    }

    fun deleteContact(){
        val contact = Contact(
            id = state.value.id.value,
            name = state.value.name.value,
            number = state.value.number.value,
            email = state.value.email.value,
            dateOfCreation = state.value.dateOfCreation.value,
            isActive = true ,
            image = state.value.image.value
        )
        viewModelScope.launch {
            database.dao.deleteContact(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dateOfCreation.value = 0
        state.value.image.value = ByteArray(0)
    }



    }

//changes1