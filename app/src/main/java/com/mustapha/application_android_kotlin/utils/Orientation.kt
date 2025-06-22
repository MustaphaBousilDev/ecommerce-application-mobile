package com.mustapha.application_android_kotlin.utils

sealed class Orientation {
    data class Vertical(
        val alignment: VerticalAlignment = VerticalAlignment.TopToBottom,
        val animationStyles: VerticalAnimationStyles = VerticalAnimationStyles.ToRight
    ): Orientation()

    data class Horizontal(
        val alignment: HorizontalAlignment = HorizontalAlignment.StartToEnd,
        val animationStyles: HorizontalAnimationStyles = HorizontalAnimationStyles.FromTop
    ): Orientation()
}