package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustapha.application_android_kotlin.R
import com.mustapha.application_android_kotlin.ui.theme.poppinsFontFamily


@Composable
fun OfferCard(){

    Card(
        modifier = Modifier.fillMaxSize(0.9f)
            .height(100.dp)
            .clip(RoundedCornerShape(2.dp))
    ) {
        Column (
           horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ){
            Text(
                text = "SALE: EXTRA 20% OFF",
                fontSize = 19.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = "erer"
            )
        }
    }
}