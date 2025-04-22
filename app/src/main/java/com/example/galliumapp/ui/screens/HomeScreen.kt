package com.example.galliumapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Bienvenido a GALLIUM", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí va tu catálogo de productos electrónicos")
    }
}
