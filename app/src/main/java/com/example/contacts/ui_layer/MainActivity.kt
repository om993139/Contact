package com.example.contacts.ui_layer

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.contacts.navigation.NavHostGraph
import com.example.contacts.ui.theme.ContactsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

private  val contactViewModel by viewModels<ContactViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val splashScreen = installSplashScreen()
        enableEdgeToEdge()
        setContent {

            splashScreen.setKeepOnScreenCondition{contactViewModel.isSplashScreen }

            val viewModel = hiltViewModel<ContactViewModel>()
            val state by viewModel.state.collectAsState()
            val navController = rememberNavController()
            ContactsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->


                    NavHostGraph(viewModel = viewModel , navController = navController, modifier = Modifier.padding(innerPadding))

                    /*AddEditScreen(state, navController = rememberNavController()){
                        viewModel.saveContact()
                    }*/


                    
                }
            }
        }
    }
}

