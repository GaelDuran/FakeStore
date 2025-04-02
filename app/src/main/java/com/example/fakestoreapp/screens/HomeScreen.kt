package com.example.fakestoreapp.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.fakestoreapp.components.ProductCard
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.services.ProductService
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Composable
fun HomeScreen(innerPadding : PaddingValues, navController: NavController){
    val BASE_URL = "https://fakestoreapi.com/"
    val scope = rememberCoroutineScope()
    var products by remember {
        mutableStateOf(listOf<Product>())
    }
    var isLoading by remember {
        mutableStateOf(true)
    }
    LaunchedEffect(key1 = true) {
        scope.launch {
            try{
                val retrofitBuilder = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                val productService = retrofitBuilder.create(ProductService::class.java)
                val result = productService.getAllProducts()
                products = result
                isLoading = false
                Log.i("RESPUESTA",result.toString())
            }
            catch (e:Exception){
                Log.e("ERROR",e.toString())
            }
        }
    }

//    Column(modifier = Modifier.padding(innerPadding)) {
//        Button(onClick = {
//            navController.navigate("product-detail")
//        }){
//            Text("Navega YA")
//        }
//    }
    if(isLoading){
        // Loading
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){

        }
        CircularProgressIndicator()
    }
    else{
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        ) {
            items(products){ product ->
                ProductCard(product = product){ productoRegresado ->
                    Log.i("ProductoRegresado", productoRegresado.id.toString())
                    navController.navigate("product-detail/$productoRegresado.id")
                }
            }
        }
    }
}

