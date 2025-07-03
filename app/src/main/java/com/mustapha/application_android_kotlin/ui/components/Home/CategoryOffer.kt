package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

data class SimplePromoItem(
    val discount: String,
    val title: String,
    val color: Color = Color(0xFFFF6B35)
)

@Composable
fun CategoryOfferLazy() {
    val promoItems = listOf(
        SimplePromoItem("50%", "Smart Shopping", Color(0xFFFF6B35)),
        SimplePromoItem("30%", "Best Deals", Color(0xFF4CAF50)),
        SimplePromoItem("70%", "Mega Sale", Color(0xFF9C27B0)),
        SimplePromoItem("40%", "Flash Sale", Color(0xFFE91E63)),
        SimplePromoItem("60%", "Weekend Special", Color(0xFF673AB7))
    )

    // Calculate height: (item height + spacing) * number of items + padding
    val itemHeight = 180.dp
    val spacing = 16.dp
    val padding = 32.dp // top and bottom padding
    val totalHeight = (itemHeight + spacing) * promoItems.size + padding

    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(totalHeight) // Dynamic height based on content
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(promoItems) { item ->
            SimplePromoCard(item)
        }
    }
}

@Composable
fun SimplePromoCard(item: SimplePromoItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(item.color),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "${item.discount} OFF\n${item.title}",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}