package com.mustapha.application_android_kotlin.ui.components.layouts

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.mustapha.application_android_kotlin.ui.theme.MugiSpacing


@Composable
fun MugiContainer(
    modifier: Modifier = Modifier,
    horizontalPadding: Dp = MugiSpacing.lg, // 24.dp default
    verticalPadding: Dp = MugiSpacing.md,   // 16.dp default
    maxWidth: Dp = 600.dp, // Max width for larger screens (tablets)
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    verticalAlignment: Arrangement.Vertical = Arrangement.spacedBy(40.dp), // default value
    isScrollable: Boolean = true,
    content: @Composable ColumnScope.() -> Unit
){
    val scrollState = rememberScrollState()
    Box(
        modifier = modifier.fillMaxSize(),


    ) {
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = maxWidth)
                .padding(
                    horizontal = horizontalPadding,
                    vertical = verticalPadding
                )
                .then(
                    if (isScrollable) {
                        Modifier.verticalScroll(scrollState)
                    } else {
                        Modifier
                    }
                ),
            horizontalAlignment = horizontalAlignment,
            verticalArrangement = verticalAlignment,
            content = content
        )
    }

}