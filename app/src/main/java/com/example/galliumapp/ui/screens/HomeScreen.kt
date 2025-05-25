package com.example.galliumapp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.galliumapp.R
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme

import androidx.compose.material.icons.filled.Menu


data class Producto(val nombre: String, val precio: String, val imagenRes: Int)

@Composable
fun HomeScreen() {
    val productosMock = listOf(
        Producto("Arduino UNO", "$90.00", R.drawable.arduino_uno),
        Producto("Diodo Zener", "$2.00", R.drawable.diodo_zener),
        Producto("Cautín", "$120.00", R.drawable.cautin),
        Producto("Resistencia", "$2.00", R.drawable.resistencia),
        Producto("Capacitor", "$5.00", R.drawable.capacitor),
        Producto("LED rojo", "$2.00", R.drawable.led_rojo)
    )

    Column(modifier = Modifier.fillMaxSize()) {


        Spacer(modifier = Modifier.height(8.dp))

        // Buscar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Buscar") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            trailingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Buscar")
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Sección de ofertas
        SectionTitle("Ofertas")
        ProductGrid(productosMock)

        // Sección recomendados
        SectionTitle("Recomendados")
        ProductGrid(productosMock.shuffled())

        Spacer(modifier = Modifier.height(8.dp))

        // Bottom nav (mock)
        BottomNavigationBar()
    }
}

@Composable
fun SectionTitle(titulo: String) {
    Text(
        text = titulo,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        color = Color(0xFF1A1B2F)
    )
}

@Composable
fun ProductGrid(productos: List<Producto>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .padding(horizontal = 8.dp),
        contentPadding = PaddingValues(4.dp)
    ) {
        items(productos) { producto ->
            Card(
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth(),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Image(
                        painter = painterResource(id = producto.imagenRes),
                        contentDescription = producto.nombre,
                        modifier = Modifier.size(60.dp)
                    )
                    Text(producto.nombre, fontSize = 12.sp)
                    Text(producto.precio, fontSize = 12.sp, color = Color.Gray)
                    Button(
                        onClick = {},
                        modifier = Modifier.padding(top = 4.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4CAF50))
                    ) {
                        Text("Añadir al carrito", fontSize = 10.sp)
                    }
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(selected = true, onClick = {}, icon = {
            Icon(painterResource(id = R.drawable.ic_home), contentDescription = "Inicio")
        })
        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(painterResource(id = R.drawable.ic_categories), contentDescription = "Categorías")
        })
        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(painterResource(id = R.drawable.ic_profile), contentDescription = "Perfil")
        })
        NavigationBarItem(selected = false, onClick = {}, icon = {
            Icon(painterResource(id = R.drawable.ic_cart), contentDescription = "Carrito")
        })
    }
}
@Composable
fun MainTitle(title: String) {
    Text(text = title, color = Color.White, fontWeight = FontWeight.Bold)
}
@Composable
fun MainTitlePreview() {
    MainTitle(title = "Gallium")
}

