package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.Image
import androidx.compose.material3.*
import androidx.compose.runtime.*
import com.mustapha.application_android_kotlin.R
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import kotlinx.coroutines.launch
import kotlin.math.abs
import com.mustapha.application_android_kotlin.database.models.Product

enum class SwipeDirection {
    UP, DOWN
}


@Composable
fun ProductSwap() {
    val drawables = listOf(
        R.drawable.promo,
        R.drawable.promo,
        R.drawable.promo,
        R.drawable.promo,
    )
    cardStack(
        cardContent = { index ->
            Image(
                painter = painterResource(id = drawables[index]),
                contentDescription = "Same Card Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(250.dp)
            )
        },
        cardCount = drawables.size
    )
}

