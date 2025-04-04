package com.example.fakestoreapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.services.ProductService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.example.fakestoreapp.models.Rating
import kotlinx.coroutines.launch

@Composable
fun ProductDetailScreen(
    innerPadding: PaddingValues,
    productId: Int
) {
    val scope = rememberCoroutineScope()
    val BASE_URL = "https://fakestoreapi.com/"
    var product by remember {
        mutableStateOf(
            Product(
                id = 0,
                description = "",
                title = "",
                image = "",
                price = 0.0,
                category = "",
                rating = Rating(rate = 0.0, count = 0)
            )
        )
    }

    LaunchedEffect(true) {
        scope.launch {
            try {
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val productService = retrofitBuilder.create(ProductService::class.java)
                val response = productService.getProductById(productId)
                product = response
            } catch (_: Exception) {
            }
        }
    }

    // Scrollable content
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(product.image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(280.dp)
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = product.title,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = product.description,
            fontSize = 14.sp,
            textAlign = TextAlign.Justify
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "$${product.price}",
            color = Color(0xFFEF5350),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { /* TODO: Añadir al carrito */ },
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(48.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEF5350))
        ) {
            Text(text = "Añadir al carrito", color = Color.White, fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(20.dp)) // espacio al final
    }
}
