package com.mustapha.application_android_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/*
* 💡 Key Concepts:

dp = Density-independent pixels (scales on different screens)
sp = Scale-independent pixels (for text, respects user font size)
Modifier = Changes how components look/behave
MaterialTheme.colorScheme.primary = App's main color
Column = Vertical layout (like a stack)
* */

//@Composable--This function creates UI elements
@Composable
fun HomeScreen() {
    //Column = Stacks items vertically (one below another)
    Column  (
        //horizontalAlignment = Alignment.CenterHorizontally = Center items left-to-right
        horizontalAlignment = Alignment.CenterHorizontally,
        //verticalArrangement = Arrangement.Center = Center items top-to-bottom
        verticalArrangement = Arrangement.Center,
        //fillMaxSize() = Use entire screen space
        //padding(16.dp) = Add 16dp space around edges
        modifier = Modifier.fillMaxSize().padding(16.dp)
        //{ = Start of Column content
    ) {
        Icon(
            imageVector = Icons.Default.Home,
            contentDescription = "Home",
            modifier = Modifier.size(48.dp),
            //tint = MaterialTheme.colorScheme.primary = Color the icon with app's primary color
            tint = MaterialTheme.colorScheme.primary
        )
        //Spacer = Empty space (like invisible box)
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text ="\uD83C\uDFE0 Welcome to My Store!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text="Features Products Coming Soon...",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}