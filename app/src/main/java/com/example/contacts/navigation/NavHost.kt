package com.example.contacts.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.contacts.ui_layer.AddEditScreen
import com.example.contacts.ui_layer.AllContactScreen
import com.example.contacts.ui_layer.ContactViewModel

@Composable
fun NavHostGraph(
    modifier: Modifier = Modifier,
    viewModel: ContactViewModel,
    navController: NavHostController



) {
    val state by viewModel.state.collectAsState()

    NavHost(
        navController = navController,
        startDestination = Routes.HomeScreen.route
    ) {

        composable(Routes.HomeScreen.route) {

            AllContactScreen(viewModel = viewModel , state = state , navController = navController)
        }

        composable(Routes.AddNewContact.route) {

            AddEditScreen(
                state = viewModel.state.collectAsState().value,
                navController = navController
            ) {
                viewModel.saveContact()
            }
        }

    }
}