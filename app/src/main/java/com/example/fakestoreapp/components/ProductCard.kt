package com.example.fakestoreapp.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.rememberAsyncImagePainter
import com.example.fakestoreapp.models.Product
import com.example.fakestoreapp.models.Rating
import com.example.fakestoreapp.ui.theme.FakeStoreAppTheme

@Composable
fun ProductCard(product: Product, onClick: (Product) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onClick(product) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(product.image),
                contentDescription = null,
                modifier = Modifier
                    .height(200.dp)  // Ajusta la altura aquí
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = product.title,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                textAlign = TextAlign.Center
            )

            Text(
                text = "$${product.price}",
                color = Color(0xFFEF5350),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            Row(modifier = Modifier.padding(top = 6.dp)) {
                repeat(product.rating.rate.toInt()) {
                    Text(text = "⭐", fontSize = 14.sp)
                }
            }
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