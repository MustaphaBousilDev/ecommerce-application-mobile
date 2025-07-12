package com.mustapha.application_android_kotlin.ui.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mustapha.application_android_kotlin.ui.theme.MugiColors
import com.mustapha.application_android_kotlin.ui.theme.MugiSpacing

enum class MugiCardVariant {
    Elevated,    // Default with shadow
    Filled,      // Filled background
    Outlined     // Border only
}


@Composable
fun MugiCard(
    modifier: Modifier = Modifier,
    variant: MugiCardVariant = MugiCardVariant.Elevated,
    containerColor: Color = when (variant) {
        MugiCardVariant.Elevated -> Color.White
        MugiCardVariant.Filled -> MugiColors.Neutral100
        MugiCardVariant.Outlined -> Color.Transparent
    },
    contentColor: Color = MugiColors.Neutral900,
    shape: Shape = RoundedCornerShape(12.dp),
    elevation: Dp = when (variant) {
        MugiCardVariant.Elevated -> 4.dp
        MugiCardVariant.Filled -> 0.dp
        MugiCardVariant.Outlined -> 0.dp
    },
    border: BorderStroke? = when (variant) {
        MugiCardVariant.Outlined -> BorderStroke(1.dp, MugiColors.Neutral500)
        else -> null
    },
    contentPadding: Dp = MugiSpacing.lg,
    content: @Composable ColumnScope.() -> Unit
    ){
    Card (
        modifier = modifier,
        shape = shape,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        border = border
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }
}