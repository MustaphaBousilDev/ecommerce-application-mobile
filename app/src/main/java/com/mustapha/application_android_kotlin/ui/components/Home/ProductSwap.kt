package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlin.math.abs
import com.mustapha.application_android_kotlin.database.models.Product

enum class SwipeDirection {
    UP, DOWN
}

@Composable
fun LazySwipeCards(
    items: List<Product>,
    modifier: Modifier = Modifier,
    maxVisibleCards: Int = 3,
    onSwipe: (Product, SwipeDirection) -> Unit = { _, _ -> },
    cardContent: @Composable (Product) -> Unit = { DefaultProductCardContent(it) }
) {
    var currentIndex by remember { mutableIntStateOf(0) }
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val swipeThreshold = screenHeight * 0.15f // Reduced threshold for smoother swiping

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // Render visible cards in reverse order (bottom to top)
        for (i in (currentIndex + maxVisibleCards - 1) downTo currentIndex) {
            val actualIndex = i % items.size // Infinite scrolling with modulo
            val cardIndex = i - currentIndex
            val scale = 1f - (cardIndex * 0.03f) // Reduced scale difference for smoother effect
            val offsetY = 6.dp * cardIndex // Reduced offset for tighter stacking

            SwipeCard(
                item = items[actualIndex],
                modifier = Modifier
                    .fillMaxWidth(0.92f)
                    .height(420.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        translationY = offsetY.toPx()
                    },
                isTopCard = cardIndex == 0,
                swipeThreshold = swipeThreshold,
                onSwipe = { direction ->
                    onSwipe(items[actualIndex], direction)
                    currentIndex++
                },
                content = cardContent
            )
        }
    }
}

@Composable
private fun SwipeCard(
    item: Product,
    modifier: Modifier = Modifier,
    isTopCard: Boolean = false,
    swipeThreshold: androidx.compose.ui.unit.Dp,
    onSwipe: (SwipeDirection) -> Unit,
    content: @Composable (Product) -> Unit
) {
    val density = LocalDensity.current
    val swipeThresholdPx = with(density) { swipeThreshold.toPx() }

    val offsetY = remember { Animatable(0f) }
    val rotation = remember { Animatable(0f) }

    val scope = rememberCoroutineScope()

    fun animateSwipe(direction: SwipeDirection) {
        scope.launch {
            val targetY = when (direction) {
                SwipeDirection.UP -> -swipeThresholdPx * 3
                SwipeDirection.DOWN -> swipeThresholdPx * 3
            }
            val targetRotation = when (direction) {
                SwipeDirection.UP -> -8f
                SwipeDirection.DOWN -> 8f
            }

            launch {
                offsetY.animateTo(targetY, animationSpec = tween(250)) // Faster animation
            }
            launch {
                rotation.animateTo(targetRotation, animationSpec = tween(250))
            }
        }.invokeOnCompletion {
            onSwipe(direction)
        }
    }

    fun resetCard() {
        scope.launch {
            launch { offsetY.animateTo(0f, animationSpec = tween(150)) } // Faster reset
            launch { rotation.animateTo(0f, animationSpec = tween(150)) }
        }
    }

    Card(
        modifier = modifier
            .offset(y = with(density) { offsetY.value.toDp() })
            .rotate(rotation.value)
            .pointerInput(Unit) {
                if (isTopCard) {
                    detectDragGestures(
                        onDragEnd = {
                            val currentOffsetY = offsetY.value

                            if (abs(currentOffsetY) > swipeThresholdPx) {
                                if (currentOffsetY > 0) {
                                    animateSwipe(SwipeDirection.DOWN)
                                } else {
                                    animateSwipe(SwipeDirection.UP)
                                }
                            } else {
                                resetCard()
                            }
                        }
                    ) { _, dragAmount ->
                        scope.launch {
                            // Only handle vertical dragging
                            val newOffsetY = offsetY.value + dragAmount.y

                            offsetY.snapTo(newOffsetY)

                            // Calculate subtle rotation based on vertical offset
                            val rotationFactor = (newOffsetY / swipeThresholdPx) * 5f
                            rotation.snapTo(rotationFactor.coerceIn(-10f, 10f))
                        }
                    }
                }
            }
            .graphicsLayer {
                // Add some transparency when swiping
                alpha = if (isTopCard) {
                    (1f - abs(offsetY.value) / (swipeThresholdPx * 2)).coerceIn(0.4f, 1f)
                } else 1f
            },
        elevation = CardDefaults.cardElevation(defaultElevation = if (isTopCard) 12.dp else 6.dp)
    ) {
        content(item)

        // Swipe indicators
        if (isTopCard) {
            SwipeIndicators(
                offsetY = offsetY.value,
                threshold = swipeThresholdPx
            )
        }
    }
}

@Composable
private fun SwipeIndicators(
    offsetY: Float,
    threshold: Float
) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Up swipe indicator
        if (offsetY < 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(24.dp)
                    .background(
                        Color.Blue.copy(alpha = (abs(offsetY) / threshold).coerceIn(0f, 0.9f)),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "⬆ NEXT",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        // Down swipe indicator
        if (offsetY > 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(24.dp)
                    .background(
                        Color.Green.copy(alpha = (offsetY / threshold).coerceIn(0f, 0.9f)),
                        RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 20.dp, vertical = 10.dp)
            ) {
                Text(
                    text = "⬇ PREVIOUS",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
private fun DefaultProductCardContent(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                androidx.compose.ui.graphics.Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF6B73FF),
                        Color(0xFF9F4FFC)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            // Top section with category and new badge
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                // Category badge
                Surface(
                    color = Color.White.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(
                        text = product.category.uppercase(),
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                // New badge
                if (product.isNew) {
                    Surface(
                        color = Color.Red,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "NEW",
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                            color = Color.White,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Center section - Product image placeholder
            Box(
                modifier = Modifier
                    .size(120.dp)
                    .background(
                        Color.White.copy(alpha = 0.1f),
                        RoundedCornerShape(16.dp)
                    )
                    .align(Alignment.CenterHorizontally),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "📦",
                    fontSize = 48.sp
                )
            }

            // Bottom section with product details
            Column {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ) {
                    Column {
                        // Price with discount
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "$${product.price}",
                                style = MaterialTheme.typography.headlineMedium,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                            product.discount?.let { discount ->
                                Text(
                                    text = " -$discount%",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Yellow,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Rating
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "⭐",
                                fontSize = 14.sp
                            )
                            Text(
                                text = " ${product.rating}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.9f)
                            )
                        }
                    }

                    // Add to cart button
                    Surface(
                        color = Color.White,
                        shape = RoundedCornerShape(24.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "🛒",
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

// Preview with sample data
@Preview
@Composable
fun LazySwipeCardsPreview() {
    val sampleProducts = listOf(
        Product("1", "Wireless Headphones", 99.99, 0, "Electronics", 4.8f, true, 20),
        Product("2", "Smart Watch", 199.99, 0, "Electronics", 4.5f, false, null),
        Product("3", "Coffee Mug", 15.99, 0, "Home", 4.2f, false, 10),
        Product("4", "Running Shoes", 79.99, 0, "Sports", 4.7f, true, 15),
        Product("5", "Laptop Stand", 49.99, 0, "Office", 4.3f, false, null),
    )

    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            LazySwipeCards(
                items = sampleProducts,
                onSwipe = { product, direction ->
                    println("Swiped ${product.name} to $direction")
                }
            )
        }
    }
}

// Example usage with custom card content
@Composable
fun CustomProductCardExample() {
    val products = listOf(
        Product("1", "Premium Headphones", 149.99, 0, "Audio", 4.9f, true, 25),
        Product("2", "Gaming Mouse", 79.99, 0, "Gaming", 4.6f, false, null),
        Product("3", "Desk Lamp", 39.99, 0, "Furniture", 4.4f, false, 15),
    )

    LazySwipeCards(
        items = products,
        onSwipe = { product, direction ->
            when (direction) {
                SwipeDirection.UP -> println("Next: ${product.name}")
                SwipeDirection.DOWN -> println("Previous: ${product.name}")
            }
        }
    ) { product ->
        // Custom minimalist card design
        Card(
            modifier = Modifier.fillMaxSize(),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = product.name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Light,
                    color = Color.Black
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "$${product.price}",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6B73FF)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = product.category,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
            }
        }
    }
}