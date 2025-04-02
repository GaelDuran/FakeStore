package com.example.fakestoreapp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit){
    Card(
        modifier = Modifier.fillMaxWidth()
            .height(200.dp)
            .padding(5.dp)
            .clickable{
                onClick(product)
            }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AsyncImage(
                model = product.image,
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().weight(3f),
                contentScale = ContentScale.Crop
            )
            Text(text = product.computedTitle,
                modifier = Modifier.weight(1f))
        }
    }
}

@Preview
@Composable
fun ProductCardPreview(){
    val testproduct = Product(
        id = 1,
        title = "iPhone 16",
        description = "Un buen teléfono",
        price = 17_000.0,
        category = "Telefonia",
        image = "",
        rating = Rating(count = 5, rate = 5.0)
    )
    FakeStoreAppTheme {
        ProductCard(product = testproduct, onClick = { println("Hola") })
    }
}