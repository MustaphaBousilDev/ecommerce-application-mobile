package com.mustapha.application_android_kotlin.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import com.mustapha.application_android_kotlin.ui.components.Home.CategoryTabs
import com.mustapha.application_android_kotlin.ui.components.Home.PromoBanner
import com.mustapha.application_android_kotlin.ui.components.Home.ProductSwap
import com.mustapha.application_android_kotlin.ui.components.Home.TopHeader

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
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
        //{ = Start of Column content
    ) {

        PromoBanner()
        Spacer(modifier = Modifier.height(16.dp))
        CategoryTabs()
        Spacer(modifier = Modifier.height(16.dp))
        ProductSwap()



    }
}