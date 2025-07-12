package com.mustapha.application_android_kotlin.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.StartOffset
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.mustapha.application_android_kotlin.R
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustapha.application_android_kotlin.ui.components.GradientBackgroundBrush
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
){
    var startAnimation by remember { mutableStateOf(false) }
    val gradientColors = listOf(
        Color(0xFFFF6B35), // Orange
        Color(0xFFFF8E00), // Orange-Yellow
        Color(0xFFFFD700)  // Gold/Yellow
    )

    val alphaAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
        label = "alpha_animation"
    )

    val scaleAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0.3f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
        label = "scale_animation"
    )

    val rotationAnimation by animateFloatAsState(
        targetValue = if (startAnimation) 0f else -180f,
        animationSpec = tween(durationMillis = 1500, easing = FastOutSlowInEasing),
        label = "rotation_animation"
    )

    val textAnimations = remember { mutableStateListOf<Animatable<Float, AnimationVector1D>>() }
    val text = "Muugiwara"
    LaunchedEffect(Unit) {
        textAnimations.clear()
        repeat(text.length) {
            textAnimations.add(Animatable(0f))
        }
    }
    LaunchedEffect(Unit) {
        startAnimation = true

        // Animate each letter with staggered delay
        textAnimations.forEachIndexed { index, animatable ->
            launch {
                delay(800 + (index * 100L)) // Stagger each letter by 100ms
                animatable.animateTo(
                    targetValue = 1f,
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
            }
        }

        // Navigate after animation completes
        delay(3500)
        onSplashFinished()
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = GradientBackgroundBrush(
                    isVerticalGradient = true,
                    colors = gradientColors
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Logo/Icon placeholder (you can replace with actual logo)
            Box(
                modifier = Modifier
                    .size(140.dp)
                    .scale(scaleAnimation)
                    .alpha(alphaAnimation)
                    .rotate(rotationAnimation)
                    ,
                contentAlignment = Alignment.Center
            ) {
                // You can replace this with your actual logo
                Image(
                    painter = painterResource(id = R.drawable.mugiwara_removebg_preview),
                    contentDescription = "luffy",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(140.dp)
                        .clip(CircleShape)
                )
            }



            // Animated text "Muugiwara"


            Spacer(modifier = Modifier.height(16.dp))

            // Subtitle
            Text(
                text = "Premium Shopping Experience",
                fontSize = 16.sp,
                color = Color.White.copy(alpha = 0.9f),
                fontWeight = FontWeight.Medium,
                modifier = Modifier.alpha(alphaAnimation),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(48.dp))

            // Loading indicator
            LoadingDots(
                modifier = Modifier.alpha(alphaAnimation)
            )
        }
    }
}

@Composable
fun LoadingDots(
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading_dots")

    val animatedValues = (0..2).map { index ->
        infiniteTransition.animateFloat(
            initialValue = 0f,
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
                animation = tween(durationMillis = 600, easing = FastOutSlowInEasing),
                repeatMode = RepeatMode.Reverse,
                initialStartOffset = StartOffset(index * 200)
            ),
            label = "dot_$index"
        )
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        animatedValues.forEach { animatedValue ->
            Box(
                modifier = Modifier
                    .size(12.dp)
                    .scale(animatedValue.value)
                    .background(
                        color = Color.White.copy(alpha = 0.8f),
                        shape = CircleShape
                    )
            )
        }
    }
}
