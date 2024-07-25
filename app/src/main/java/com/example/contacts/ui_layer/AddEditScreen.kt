package com.example.contacts.ui_layer


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.renderscript.Sampler.Value
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.contacts.R
import com.example.contacts.navigation.Routes
import java.io.ByteArrayOutputStream
import java.io.InputStream


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEditScreen(
    state: ContactState,
    navController: NavController,
    onEvent: () -> Unit
){

    val context = LocalContext.current


    fun compressImage(context: Context, uri: Uri, compressionQuality: Int = 50): ByteArray? {
        val inputStream: InputStream? = context.contentResolver.openInputStream(uri)
        val bitmap: Bitmap? = BitmapFactory.decodeStream(inputStream)

        return if (bitmap != null) {
            val outputStream = ByteArrayOutputStream()
            // Compress the bitmap, you can change the format to PNG if needed
            bitmap.compress(Bitmap.CompressFormat.JPEG, compressionQuality, outputStream)
            outputStream.toByteArray()
        } else {
            null
        }
    }

    val pickMedia =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.PickVisualMedia()) { uri ->
            if (uri != null) {
                val inputStream: InputStream? = uri.let {
                    context.contentResolver.openInputStream(it)
                }
                val byte = inputStream?.readBytes()
                if (byte != null) {
                    //state.image.value = byte
                    val compressedImage = compressImage(context , uri)
                    if (compressedImage != null) {
                        if (compressedImage.size > 1024 * 1024) { // 1MB
                            Toast.makeText(
                                context,
                                "Image size is large . Pick a small image",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            state.image.value = compressedImage
                        }
                    }
                }
            }
        }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = " Add Contacts Screen", color = Color.White) },
                navigationIcon = {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null , tint = Color.White,
                        modifier = Modifier.clickable {
                            navController.navigate(Routes.HomeScreen.route)
                        }

                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF212121)) // Dark Gray
            )

        }

    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF000000), // Black
                            Color(0xFF1b1b1b), // Dark Gray
                            Color(0xFF1b1b1b), // Dark Gray
                            Color(0xFF1b1b1b), // Dark Gray
                            Color(0xFF3d3d3d)  // Lighter Gray
                        )
                    )
                )

            //0xFF2F1A49 --  // Deep Purple
            //0xFF3d3d3d // Lighter Gray
            //0xFF212121 // Dark Gray color for top app bar
            //0xFF4A1F61 // Deep Purple
            //0xFF880E4F // Dark Red
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {


                val image = state.image.value.let {
                    BitmapFactory.decodeByteArray(it, 0, it.size)
                }?.asImageBitmap()

                Box(
                    modifier = Modifier.size(150.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    if (image != null) {
                        Image(
                            bitmap = image,
                            contentDescription = "Contact image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Contact image",
                            modifier = Modifier
                                .size(140.dp)
                                .clip(CircleShape)
                                .background(Color.Gray)
                                .padding(24.dp),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                    IconButton(
                        onClick = {
                            pickMedia.launch(
                                androidx.activity.result.PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        },
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.primary)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White
                        )
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.name.value,
                    onValueChange = { state.name.value = it },
                    placeholder = { Text(text = "ex.John Due", color = Color.Gray) },
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Person, contentDescription = "name")
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    singleLine = true,
                    shape = RoundedCornerShape(23.dp),
                    label = { Text(text = "Enter Name") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF80CBC4),
                        unfocusedBorderColor = Color(0xFF80CBC4),
                        focusedLabelColor = Color(0xFF80CBC4),
                        unfocusedLabelColor = Color(0xFF80CBC4),
                        focusedLeadingIconColor = Color(0xFF80CBC4),
                        unfocusedLeadingIconColor = Color(0xFF80CBC4),
                        //placeholderColor = Color.Gray
                    ),
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.number.value,
                    onValueChange = { state.number.value = it },
                    placeholder = { Text(text = "+91 1234567890", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    leadingIcon = {
                        Icon(painter = painterResource(id = R.drawable.phone_call), contentDescription = "call")
                        //Icon(imageVector = Icons.Default.Phone, contentDescription = "phone")
                    },
                    shape = RoundedCornerShape(23.dp),
                    label = { Text(text = "Enter Number") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF80CBC4),
                        unfocusedBorderColor = Color(0xFF80CBC4),
                        focusedLabelColor = Color(0xFF80CBC4),
                        unfocusedLabelColor = Color(0xFF80CBC4),
                        focusedLeadingIconColor = Color(0xFF80CBC4),
                        unfocusedLeadingIconColor = Color(0xFF80CBC4),
                        //placeholderColor = Color.Gray
                    ),
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = state.email.value,
                    onValueChange = { state.email.value = it },
                    placeholder = { Text(text = "email@example.com", color = Color.Gray) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    leadingIcon = {

                        Icon(painter = painterResource(id = R.drawable.email1), contentDescription = null )
                        //Icon(imageVector = Icons.Default.Email, contentDescription = "email")
                    },
                    shape = RoundedCornerShape(23.dp),
                    singleLine = true,
                    label = { Text(text = "Enter Email") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color(0xFF80CBC4),
                        unfocusedBorderColor = Color(0xFF80CBC4),
                        focusedLabelColor = Color(0xFF80CBC4),
                        unfocusedLabelColor = Color(0xFF80CBC4),
                        focusedLeadingIconColor = Color(0xFF80CBC4),
                        unfocusedLeadingIconColor = Color(0xFF80CBC4),
                        //placeholderColor = Color.Gray
                    ),
                    textStyle = TextStyle(color = Color.White),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(onClick = {

                    onEvent.invoke()
                    navController.navigateUp()
                    Toast.makeText(context, "Contact Saved Successfully !!", Toast.LENGTH_SHORT).show()
                }) {
                    Text(text = "Save Contact")
                }
            }
        }
    }
}




