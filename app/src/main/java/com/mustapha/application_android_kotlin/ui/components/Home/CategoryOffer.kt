package com.mustapha.application_android_kotlin.ui.components.Home


import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import coil.compose.AsyncImage
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustapha.application_android_kotlin.R
import kotlinx.coroutines.delay

data class SimplePromoItem(
    val discount: String,
    val title: String,
    val color: Color = Color(0xFFFF6B35),
    val images: List<Int>,
)

@Composable
fun CategoryOfferLazy() {
    val promoItems = listOf(
        SimplePromoItem(
            "50%",
            "Smart Shopping",
            Color(0xFFFF6B35),
            listOf(
                R.drawable.swipe1,
                R.drawable.swipe2,
                R.drawable.swipe3,
                R.drawable.swipe4,
                R.drawable.swipe5,
                R.drawable.swipe6
            ),
            ),
        SimplePromoItem(
            "50%",
            "Smart Shopping",
            Color(0xFFFF6B35),
            listOf(
                R.drawable.swipe1,
                R.drawable.swipe2,
                R.drawable.swipe3,
                R.drawable.swipe4,
                R.drawable.swipe5,
                R.drawable.swipe6
            ),
        ),
        SimplePromoItem(
            "50%",
            "Smart Shopping",
            Color(0xFFFF6B35),
            listOf(
                R.drawable.swipe1,
                R.drawable.swipe2,
                R.drawable.swipe3,
                R.drawable.swipe4,
                R.drawable.swipe5,
                R.drawable.swipe6
            ),
        ),
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
            AnimatedPromoCard(item)
        }
    }
}



@Composable
fun AnimatedPromoCard(item: SimplePromoItem) {
    var currentImageIndex by remember { mutableStateOf(0) }
    var isTransitioning by remember { mutableStateOf(false) }

    // Animation for opacity
    val alpha by animateFloatAsState(
        targetValue = if (isTransitioning) 0f else 1f,
        animationSpec = tween(
            durationMillis = 10, // 500ms fade duration
            easing = FastOutSlowInEasing
        ),
        finishedListener = {
            if (isTransitioning) {
                // Change image when fade out completes
                currentImageIndex = (currentImageIndex + 1) % item.images.size
                isTransitioning = false
            }
        }
    )

    // Timer effect for changing images every 3 seconds
    LaunchedEffect(currentImageIndex) {
        delay(2000) // Wait 3 seconds
        isTransitioning = true // Start fade out
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(30.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            // Background color (visible during transitions)


            // Animated image
            AsyncImage(
                model = item.images[currentImageIndex],
                contentDescription = item.title,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(alpha)
            )

            // Overlay with text

        }
    }
}