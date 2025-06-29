package com.mustapha.application_android_kotlin.ui.components

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.AnimationVector1D
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.mustapha.application_android_kotlin.utils.HorizontalAlignment
import com.mustapha.application_android_kotlin.utils.HorizontalAnimationStyles
import com.mustapha.application_android_kotlin.utils.Orientation
import com.mustapha.application_android_kotlin.utils.VerticalAlignment
import com.mustapha.application_android_kotlin.utils.VerticalAnimationStyles
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val rotationValue = 45f;
private const val defaultAnimationDuration = 300;
private val defaultPaddingBetweenItems = 8.dp
private val defaultCardElevation = 1.dp;

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun cardStack(
    cardContent: @Composable (Int) -> Unit,
    cardCount: Int,
    cardElevation: Dp = defaultCardElevation,
    paddingBetweenCards: Dp = defaultPaddingBetweenItems,
    animationDuration: Int = defaultAnimationDuration,
    cardShape: Shape = MaterialTheme.shapes.medium,
    cardBorder: BorderStroke? = null,
    onCardClick: ((Int) -> Unit)? = null,
    orientation: Orientation = Orientation.Vertical()
) {
    checkCardCount(cardCount)
    checkPadding(paddingBetweenCards)
    checkAnimationDuration(animationDuration = animationDuration)
    val runAnimations = animationDuration > 0;
    val coroutineScope  = rememberCoroutineScope()
    var selectedIndex by rememberSaveable {
        mutableStateOf(0)
    }
    val contentAlignment = getContentAlignment(orientation)
    val rotationValue = getRotation(orientation)

    Box(
        contentAlignment= contentAlignment,








    ) {
        (0 until cardCount).forEachIndexed { index, _ ->
            ShowCard(
                coroutineScope = coroutineScope,
                runAnimations = runAnimations,
                selectedIndex = selectedIndex,
                index = index,
                cardCount = cardCount,
                paddingBetweenCards = paddingBetweenCards,
                animationDuration = animationDuration,
                rotationValue = rotationValue,
                orientation = orientation,
                cardElevation = cardElevation,
                cardShape = cardShape,
                cardBorder = cardBorder,
                onCardClick = onCardClick,
                composable = { cardContent(index) },
                newIndexBlock = { selectedIndex = it }
            )
        }
    }

}
@ExperimentalMaterial3Api
@Composable
private fun ShowCard(coroutineScope: CoroutineScope,
                     runAnimations: Boolean,
                     selectedIndex: Int,
                     index: Int,
                     cardCount: Int,
                     paddingBetweenCards: Dp,
                     animationDuration: Int,
                     rotationValue: Float,
                     orientation: Orientation,
                     cardElevation: Dp,
                     cardShape: Shape,
                     cardBorder: BorderStroke?,
                     onCardClick: ((Int) -> Unit)? = null,
                     composable: @Composable (Int) -> Unit,
                     newIndexBlock: (Int) -> Unit) {
    var itemPxSize = 0

    val padding = when {
        selectedIndex == index -> 0.dp
        selectedIndex < index -> ((index - selectedIndex) * paddingBetweenCards.value).dp
        selectedIndex > index -> ((cardCount - selectedIndex + index) * paddingBetweenCards.value).dp
        else -> throw IllegalStateException()
    }

    val paddingAnimation by animateDpAsState(padding,
        tween(animationDuration, easing = FastOutSlowInEasing)
    )
    val offsetAnimation = remember { Animatable(0f) }
    val rotateAnimation = remember { Animatable(0f) }

    val offsetValues = when (orientation) {
        is Orientation.Vertical -> {
            IntOffset(
                if (orientation.animationStyles == VerticalAnimationStyles.ToRight)
                    offsetAnimation.value.toInt()
                else
                    -offsetAnimation.value.toInt(), 0
            )
        }
        is Orientation.Horizontal -> {
            IntOffset(
                0, if (orientation.animationStyles == HorizontalAnimationStyles.FromTop)
                    -offsetAnimation.value.toInt()
                else
                    offsetAnimation.value.toInt()
            )
        }
    }

    val paddingModifier = when {
        orientation is Orientation.Vertical && orientation.alignment == VerticalAlignment.TopToBottom -> PaddingValues(top = paddingAnimation)
        orientation is Orientation.Vertical && orientation.alignment == VerticalAlignment.BottomToTop -> PaddingValues(bottom = paddingAnimation)
        orientation is Orientation.Horizontal && orientation.alignment == HorizontalAlignment.StartToEnd -> PaddingValues(start = paddingAnimation)
        else -> PaddingValues(end = paddingAnimation)
    }

    val modifier = Modifier
        .padding(paddingModifier)
        .zIndex(-padding.value)
        .offset(){ offsetValues }

        .rotate(rotateAnimation.value)
        .clip(RoundedCornerShape(100.dp))

        .onSizeChanged() {
            itemPxSize = if (orientation is Orientation.Vertical) {
                if (itemPxSize > it.width)
                    itemPxSize
                else
                    it.width
            } else {
                if (itemPxSize > it.height)
                    itemPxSize
                else
                    it.height
            }
        }

    Card(elevation = androidx.compose.material3.CardDefaults.cardElevation(defaultElevation = cardElevation),
        shape = cardShape,
        modifier = modifier,
        border = cardBorder,
        onClick =  {
            if(cardCount > 1 && selectedIndex == index) {
                onCardClick?.invoke(index)
                animateOnClick(coroutineScope, itemPxSize, runAnimations, animationDuration, rotationValue, index, cardCount, offsetAnimation, rotateAnimation, newIndexBlock)
            }
        }
    ) {
        composable.invoke(index)
    }
}

private fun checkCardCount(cardCount: Int) {
    if (cardCount < 2) {
        throw IllegalArgumentException("Can't use 1 or less card count!")
    }
}

private fun checkAnimationDuration(animationDuration: Int) {
    if (animationDuration < 1)
        throw IllegalArgumentException("Can't use 1 or less duration animation")
}

private fun getContentAlignment (orientation: Orientation): Alignment {
    return when (orientation) {
        is Orientation.Vertical -> {
            if (orientation.alignment == VerticalAlignment.TopToBottom)
                Alignment.TopCenter
            else
                Alignment.BottomCenter
        }
         is Orientation.Horizontal -> {
             if (orientation.alignment == HorizontalAlignment.StartToEnd)
                 Alignment.CenterStart
             else
                 Alignment.CenterEnd
         }
    }
}

private fun getRotation(orientation: Orientation): Float {
    return when (orientation) {
        is Orientation.Vertical -> {
            if (orientation.animationStyles == VerticalAnimationStyles.ToRight)
                rotationValue
            else
                -rotationValue
        }
         is Orientation.Horizontal -> {
             if (orientation.animationStyles == HorizontalAnimationStyles.FromTop)
                 -rotationValue
             else
                 rotationValue
         }
    }
}

private fun checkPadding(paddingBetweenCards: Dp) {
    if (paddingBetweenCards <= 0.dp) {
        throw IllegalArgumentException("Can't use 0 or less for padding between cards")
    }
}

private fun animateOnClick(
    coroutineScope: CoroutineScope,
    pxValue: Int,
    runAnimations: Boolean,
    animationDuration: Int,
    rotationValue: Float,
    index: Int,
    cardCount: Int,
    offsetAnimation: Animatable<Float, AnimationVector1D>,
    rotateAnimation: Animatable<Float, AnimationVector1D>,
    newIndexBlock: (Int) -> Unit
) {
    val spec: TweenSpec<Float> = tween(animationDuration, easing = FastOutLinearInEasing)

    coroutineScope.launch {
        if (runAnimations)
            offsetAnimation.animateTo(pxValue.toFloat(), spec)

        val newIndex = if (cardCount > index + 1)
            index + 1
        else
            0

        newIndexBlock.invoke(newIndex)

        if(runAnimations) {
            rotateAnimation.animateTo(rotationValue, spec)
            launch { rotateAnimation.animateTo(0f, spec) }
            launch { offsetAnimation.animateTo(0f, spec) }
        }
    }
}
