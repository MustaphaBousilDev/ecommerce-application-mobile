package com.mustapha.application_android_kotlin.ui.components.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mustapha.application_android_kotlin.ui.theme.MugiColors


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MugiScaffold(
    modifier: Modifier = Modifier,
    topBar: (@Composable () -> Unit) = {},
    bottomBar: (@Composable () -> Unit) = {},
    snackbarHost: (@Composable () -> Unit) = {},
    floatingActionButton : (@Composable () -> Unit)  = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = MugiColors.Neutral100,
    contentColor: Color = MugiColors.Neutral900,
    isLoading: Boolean = false,
    content: @Composable (PaddingValues) -> Unit
    ){
    Scaffold (
        modifier = modifier,
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        content = { paddingValues ->
            Box {
                // Main content
                content(paddingValues)

                // Global loading overlay (if needed)
                if (isLoading) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.Black.copy(alpha = 0.5f)),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = MugiColors.Primary500
                        )
                    }
                }
            }
        }

    )
}