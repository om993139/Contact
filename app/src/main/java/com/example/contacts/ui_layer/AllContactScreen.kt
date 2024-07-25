package com.example.contacts.ui_layer

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.navigation.Routes
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllContactScreen(
    viewModel: ContactViewModel,
    state: ContactState,
    navController: NavController
) {

    val context = LocalContext.current
    //val listState = rememberLazyListState()






        Scaffold(
            topBar = {
                TopAppBar(title = { Text("Contacts", color = Color.White) },
                    actions = {
                        Icon(
                            modifier = Modifier.clickable {

                            },
                            imageVector = Icons.Default.Search,
                            contentDescription = "search",
                            tint = Color.White
                        )
                        Spacer(modifier = Modifier.size(15.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_sort_24),
                            contentDescription = "Sort",
                            tint = Color.White,
                            modifier = Modifier.clickable {
                                viewModel.changeSorting()
                            })

                    },

                    colors = TopAppBarDefaults.topAppBarColors(

                        containerColor = Color(0xFF3C3C3C)


                        //containerColor = Color(0xFF1F1F1F)
                        //0xFF1F1F1F -- Top Bar Color
                    ),

                    navigationIcon = {

                        IconButton(onClick = {

                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = null,
                                tint = Color.White
                            )
                        }
                    }
                )
            },


            floatingActionButton = {

                FloatingActionButton(
                    onClick = {
                        navController.navigate(Routes.AddNewContact.route)
                    },
                    containerColor = Color(0xFF6200EA),
                    modifier = Modifier.padding(bottom = 64.dp)
                ) {


                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            },
            containerColor = Color.Transparent
        ) { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF000000),
                                Color(0xFF434343)
                            )
                        )
                    )
                    .padding(innerPadding)
            ) {


                LazyColumn(
                   
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp)


                ) {
                    items(state.contact) {

                        val bitmap = it.image?.let {
                            BitmapFactory.decodeByteArray(it, 0, it.size)
                        }


                        Card(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            elevation = CardDefaults.cardElevation(8.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFF2C2C2C)
                            )
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {


                                if (bitmap != null) {
                                    Image(
                                        bitmap = bitmap.asImageBitmap(),
                                        contentDescription = "Contact image",
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier
                                            .size(67.dp)
                                            .clip(CircleShape)
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.Person,
                                        contentDescription = "Contact image",
                                        modifier = Modifier
                                            .size(67.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.onPrimaryContainer)
                                            .padding(10.dp),
                                        tint =
                                        MaterialTheme.colorScheme.onPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.width(8.dp))


                                Column(
                                    modifier = Modifier

                                        .clickable {
                                            state.id.value = it.id
                                            state.number.value = it.number
                                            state.name.value = it.name
                                            state.email.value = it.email
                                            state.dateOfCreation.value = it.dateOfCreation
                                            navController.navigate(Routes.AddNewContact.route)
                                        }
                                ) {
                                    Text(
                                        text = it.name,
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = it.number,
                                        fontSize = 18.sp,
                                        color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = it.email,
                                        fontSize = 14.sp,
                                        color = Color.White
                                    )

                                }

                                Spacer(modifier = Modifier.width(12.dp))


                                Column(
                                    verticalArrangement = Arrangement.Center,
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = null,
                                        tint = Color.Red,
                                        modifier = Modifier.clickable {
                                            state.id.value = it.id
                                            state.number.value = it.number
                                            state.name.value = it.name
                                            state.email.value = it.email
                                            state.dateOfCreation.value = it.dateOfCreation
                                            viewModel.deleteContact()
                                        }
                                    )

                                    Spacer(modifier = Modifier.height(10.dp))

                                    IconButton(onClick = {
                                        val intent = Intent(Intent.ACTION_CALL)
                                        intent.data = Uri.parse("tel:${it.number}")
                                        context.startActivity(intent)
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Call,
                                            contentDescription = "Call",
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }


                            }
                        }
                    }
                }
            }
        }
    }



