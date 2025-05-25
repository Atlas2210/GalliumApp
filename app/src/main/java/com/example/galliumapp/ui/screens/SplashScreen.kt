package com.example.galliumapp.ui.screens

import android.os.Handler
import android.os.Looper
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.navigation.NavController
import com.example.galliumapp.R
import com.example.galliumapp.navigation.Routes

@Composable
fun SplashScreen(
    navController: NavController,
    backgroundColor: Color = Color(0xFF161D27),
    titleTextStyle: TextStyle = TextStyle(
        fontSize = 32.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontFamily = FontFamily.Default
    ),
    subtitleTextStyle: TextStyle = TextStyle(
        fontSize = 16.sp,
        color = Color.Blue,
        fontFamily = FontFamily.Default
    ),
    loadingTextStyle: TextStyle = TextStyle(
        fontSize = 14.sp,
        color = Color.White,
        fontFamily = FontFamily.Default
    )
) {
    LaunchedEffect(Unit) {
        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(Routes.Login.route) {
                popUpTo(Routes.Splash.route) { inclusive = true }
            }
        }, 2000)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("GALLIUM", style = titleTextStyle)
        Text("Tu tienda de componentes", style = subtitleTextStyle)
        Spacer(modifier = Modifier.height(16.dp))
        Image(painter = painterResource(id = R.drawable.gallium), contentDescription = null)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Cargando...", style = loadingTextStyle)
    }
}
