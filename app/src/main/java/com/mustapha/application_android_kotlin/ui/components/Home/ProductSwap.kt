package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.mustapha.application_android_kotlin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.mustapha.application_android_kotlin.ui.components.cardStack
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import kotlinx.coroutines.launch
import kotlin.math.abs
import com.mustapha.application_android_kotlin.database.models.Product

enum class SwipeDirection {
    UP, DOWN
}

data class ProductOffer(
    val title: String,
    val category: String,
    val imageRes: Int,
    val colorCard: Color,
    val price: String
)

@Composable
fun ProductSwap() {

    val drawables = listOf(
        ProductOffer(
            title="AirFlex Pro Runner",
            category = "Sports",
            imageRes = R.drawable.ee2,
            colorCard = Color.Red,
            price = "Starting at \$126.6"
        ),
        ProductOffer(
            title="AirFlex Pro Runner",
            category = "Clothes",
            imageRes = R.drawable.ee3,
            colorCard = Color.Blue,
            price = "Starting at \$62.3"
        ),
        ProductOffer(
            title="AirFlex Pro Runner",
            category = "Watch",
            imageRes = R.drawable.ee4,
            colorCard = Color.Yellow,
            price = "Starting at \$62.9"
        ),
        ProductOffer(
            title="AirFlex Pro Runner",
            category = "MacBook M5",
            imageRes = R.drawable.ee,
            colorCard = Color.Green,
            price = "Starting at \$64.8"
        ),
        ProductOffer(
            title="AirFlex Pro Runner",
            category = "MacBook M5",
            imageRes = R.drawable.promo,
            colorCard = Color.Green,
            price = "Starting at \$64.8"
        )

    )
    cardStack(
        cardContent = { index ->
            Box(
                modifier = Modifier.size(width = 380.dp, height =120.dp)

                    .background(Color(0xFFEFEEEE))
                    .clip(RoundedCornerShape(120.dp)),
                contentAlignment = Alignment.CenterStart,



            ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.Bottom,
                        modifier = Modifier.fillMaxSize().padding(0.dp)
                    ) {

                        Image(
                            painter = painterResource(id = drawables[index].imageRes),
                            contentDescription = "Same Card Image",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.width(100.dp).height(100.dp).zIndex(0f)
                        )
                        Column (
                            Modifier.padding(10.dp),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ){
                            Text(
                                text = drawables[index].title,
                                color = Color.Black,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium,
                            )
                            Text(
                                text = drawables[index].price,
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                            )
                            Row (
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ){
                                Button(onClick = {  }, colors = ButtonDefaults.buttonColors(containerColor = Color(
                                    0xFFFF5900
                                )
                                )) {
                                    Text(
                                        text = "Buy now",
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                IconButton(onClick = {}, colors = IconButtonDefaults.iconButtonColors(
                                    containerColor = Color.Black,
                                )) {
                                    Icon(
                                        imageVector = Icons.Filled.ShoppingCart,
                                        contentDescription = "Shopping Cart",
                                        tint = Color.White,
                                        modifier = Modifier.size(32.dp).padding(5.dp)
                                    )
                                }
                            }

                        }
                    }

            }
        },
        cardCount = drawables.size
    )
}

