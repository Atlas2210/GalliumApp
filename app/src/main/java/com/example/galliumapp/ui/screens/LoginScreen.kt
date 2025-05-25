package com.example.galliumapp.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.galliumapp.data.AppDatabase
import com.example.galliumapp.navigation.Routes
import com.example.galliumapp.viewmodel.UserViewModel
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController) {
    var user by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    val context = LocalContext.current
    val db = remember { AppDatabase.getDatabase(context) }
    val viewModel = remember { UserViewModel(db) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("GALLIUM", fontSize = 32.sp, color = Color.Blue)
        Spacer(modifier = Modifier.height(154.dp))

        OutlinedTextField(value = user, onValueChange = { user = it }, label = { Text("Usuario") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = pass,
            onValueChange = { pass = it },
            label = { Text("Contraseña") },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            scope.launch {
                if (viewModel.loginUser(user, pass)) {
                    navController.navigate(Routes.Home.route)
                } else {
                    Toast.makeText(context, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show()
                }
            }
        }) {
            Text("Iniciar Sesión")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = {
            viewModel.insertUser(user, pass)
            Toast.makeText(context, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
        }) {
            Text("Crear Cuenta")
        }
    }
}
