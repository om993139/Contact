package com.example.contacts.navigation

sealed class Routes(var route: String) {

    object HomeScreen : Routes("HomeScreen")

    object AddNewContact: Routes("AddNewContact")
}