package com.mustapha.application_android_kotlin.ui.components.Home



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.mustapha.application_android_kotlin.R
import com.mustapha.application_android_kotlin.database.models.Product
import kotlinx.coroutines.launch
import kotlin.math.abs
import kotlin.math.min

// Data class for Product (same as before)

@Composable
fun VerticalStackedProductCards(
    modifier: Modifier = Modifier,
    onProductClick: (Product) -> Unit = {},
    onBuyNowClick: (Product) -> Unit = {},
    onAddToCartClick: (Product) -> Unit = {}
) {
    // Sample product data
    val products = remember {
        listOf(
            Product(
                id = "1",
                name = "AirFlex Pro Runner",
                price = 129.00,
                imageRes = R.drawable.promo,
                category = "Running",
                isNew = true,
                rating = 4.8f
            ),
            Product(
                id = "2",
                name = "Urban Street Walker",
                price = 89.99,
                imageRes = R.drawable.promo,
                category = "Casual",
                rating = 4.6f
            ),
            Product(
                id = "3",
                name = "Elite Sport Pro",
                price = 199.99,
                imageRes = R.drawable.promo,
                category = "Sports",
                isNew = true,
                rating = 4.9f,
                discount = 15
            ),
            Product(
                id = "4",
                name = "Classic Comfort",
                price = 75.50,
                imageRes = R.drawable.promo,
                category = "Comfort",
                rating = 4.4f
            ),
            Product(
                id = "5",
                name = "Sport Elite Max",
                price = 159.99,
                imageRes = R.drawable.promo,
                category = "Running",
                rating = 4.7f
            )
        )
    }

    var currentIndex by remember { mutableStateOf(0) }
    var dragOffset by remember { mutableStateOf(0f) }
    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    // Animation constants
    val cardHeight = 220.dp // Reduced even more
    val stackOffset = 24.dp // Reduced for tighter stacking
    val dragThreshold = with(density) { 50.dp.toPx() } // Very sensitive for one-direction

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(cardHeight + stackOffset * 3) // Only space for cards above
            .pointerInput(currentIndex) {
                detectDragGestures(
                    onDragEnd = {
                        coroutineScope.launch {
                            // Only allow swiping up (negative dragOffset)
                            if (dragOffset < -dragThreshold) {
                                // Go to next product, cycle to first if at end
                                currentIndex = (currentIndex + 1) % products.size
                            }
                            dragOffset = 0f
                        }
                    }
                ) { _, dragAmount ->
                    // Only register upward swipes (negative values)
                    if (dragAmount.y < 0) {
                        dragOffset += dragAmount.y
                    }
                }
            }
    ) {
        // Render cards - only show current and previous cards (stacked above)
        for (i in products.indices.reversed()) {
            val product = products[i]
            // Calculate how many cards behind current card this is
            val cardsBehind = if (i <= currentIndex) {
                currentIndex - i
            } else {
                // Handle cycling - cards from end of list when current is at beginning
                products.size - (i - currentIndex)
            }

            // Only show current card and up to 3 previous cards
            val isVisible = cardsBehind <= 3

            if (isVisible) {
                val offsetY = -stackOffset * cardsBehind // Negative to position above
                val scale = 1f - (cardsBehind * 0.03f)
                val alpha = 1f - (cardsBehind * 0.15f)

                // Only current card moves with drag
                val dynamicOffsetY = if (cardsBehind == 0) dragOffset else 0f

                ProductStackCard(
                    product = product,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(cardHeight)
                        .graphicsLayer {
                            translationY = offsetY.toPx() + dynamicOffsetY
                            scaleX = scale
                            scaleY = scale
                            this.alpha = alpha
                        }
                        .zIndex(products.size - cardsBehind.toFloat()),
                    onClick = { onProductClick(product) },
                    onBuyNowClick = { onBuyNowClick(product) },
                    onAddToCartClick = { onAddToCartClick(product) },
                    isTopCard = cardsBehind == 0,
                    cardIndex = cardsBehind
                )
            }
        }

        // Swipe hint (only for first use)
        if (currentIndex == 0) {
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
                    .background(
                        Color.Black.copy(alpha = 0.7f),
                        RoundedCornerShape(16.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                Text(
                    text = "Swipe up to browse",
                    fontSize = 12.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
private fun ProductStackCard(
    product: Product,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onBuyNowClick: () -> Unit,
    onAddToCartClick: () -> Unit,
    isTopCard: Boolean = false,
    cardIndex: Int = 0
) {
    val elevation = when (cardIndex) {
        0 -> 16.dp // Top card highest elevation
        1 -> 12.dp
        2 -> 8.dp
        else -> 4.dp
    }

    Card(
        modifier = modifier.padding(horizontal = 16.dp), // Reduced padding
        shape = RoundedCornerShape(20.dp), // Slightly reduced
        elevation = CardDefaults.cardElevation(defaultElevation = elevation),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFF8F9FA),
                            Color.White
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Reduced padding
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // New Badge and Price Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    // New Badge
                    if (product.isNew) {
                        Surface(
                            color = Color(0xFF4CAF50),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(
                                text = "NEW",
                                color = Color.White,
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                            )
                        }
                    } else {
                        Spacer(modifier = Modifier.width(1.dp))
                    }

                    // Price
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        if (product.discount != null) {
                            Text(
                                text = "${(product.price * 1.15).toInt()}",
                                fontSize = 11.sp,
                                color = Color.Gray,
                                fontWeight = FontWeight.Medium
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                        }
                        Text(
                            text = "${product.price.toInt()}.${String.format("%02d", ((product.price % 1) * 100).toInt())}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFFF6B35)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Product Image
                Box(
                    modifier = Modifier
                        .size(100.dp) // Further reduced
                        .clip(CircleShape)
                        .background(
                            Brush.radialGradient(
                                colors = listOf(
                                    Color(0xFFE3F2FD),
                                    Color(0xFFBBDEFB)
                                )
                            )
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = product.imageRes),
                        contentDescription = product.name,
                        modifier = Modifier
                            .size(75.dp) // Further reduced
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // Product Name
                Text(
                    text = product.name,
                    fontSize = 16.sp, // Reduced
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )

                Spacer(modifier = Modifier.height(2.dp))

                // Category
                Text(
                    text = product.category,
                    fontSize = 11.sp, // Reduced
                    color = Color.Gray,
                    fontWeight = FontWeight.Medium
                )

                Spacer(modifier = Modifier.weight(1f))

                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    // Buy Now Button
                    Button(
                        onClick = onBuyNowClick,
                        modifier = Modifier
                            .weight(1f)
                            .height(38.dp), // Reduced
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF6B35)
                        ),
                        shape = RoundedCornerShape(19.dp)
                    ) {
                        Text(
                            text = "Buy now",
                            color = Color.White,
                            fontSize = 12.sp, // Reduced
                            fontWeight = FontWeight.Bold
                        )
                    }

                    // Add to Cart Button
                    Button(
                        onClick = onAddToCartClick,
                        modifier = Modifier.size(38.dp), // Reduced
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
                        ),
                        shape = CircleShape,
                        contentPadding = PaddingValues(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Add to Cart",
                            tint = Color.White,
                            modifier = Modifier.size(18.dp) // Reduced
                        )
                    }
                }
            }
        }
    }
}

// Usage Example
@Composable
fun ProductScreenVertical() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Featured Products",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }

        // Vertical Stacked Cards
        VerticalStackedProductCards(
            onProductClick = { product ->
                println("Product clicked: ${product.name}")
            },
            onBuyNowClick = { product ->
                println("Buy now clicked: ${product.name}")
            },
            onAddToCartClick = { product ->
                println("Add to cart clicked: ${product.name}")
            }
        )
    }
}