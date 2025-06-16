package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PromoBanner(

){
  Card(
    modifier = Modifier.fillMaxSize()
      .height(300.dp),
    shape = RoundedCornerShape(
      topStart = 0.dp,
      topEnd = 0.dp,
      bottomStart = 24.dp,
      bottomEnd = 24.dp,
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxSize()
        .background(
          brush = Brush.horizontalGradient(
            colors = listOf(
              Color(0xFF424242),
              Color(0xFF212121)
            )
          )
        )
    ){
      Row(
        modifier = Modifier
          .fillMaxSize()
          .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Row (
          verticalAlignment = Alignment.CenterVertically
        ){
          Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "ShopEase Logo",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text="ShopEase",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
          )
        }
        Row(

        ){
          IconButton(onClick = {}) {
            Icon(
              imageVector = Icons.Default.FavoriteBorder,
              contentDescription = "Favorites",
              tint = Color.White,
              modifier = Modifier.size(24.dp)
            )
          }
          BadgedBox(
            badge = {
              Badge(
                containerColor = Color.Red,
                contentColor = Color.White
              ) {
                Text(
                  text="2",
                  fontSize = 10.sp
                )
              }
            }
          ) {
            IconButton(
              onClick = {}
            ) {
              Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
              )
            }
          }
        }
      }
      Spacer(modifier = Modifier.height(16.dp))
      Row (
        modifier = Modifier.fillMaxSize()
          .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
      ){
        Column(
          modifier = Modifier.weight(1f),
          verticalArrangement = Arrangement.Center
        ) {
          Text(
            text="50%",
            color = Color(0xFFFF6B35),
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold
          )
          Text(
            text="OFF",
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium
          )
          Spacer(modifier = Modifier.height(12.dp))
          Button(
            onClick = {},
            colors = ButtonDefaults.buttonColors(
              containerColor = Color(0xFFFF6B35)
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier
              .height(36.dp)
              .width(100.dp)
          ) {
            Text(
              text="Shop now",
              color = Color.White,
              fontSize = 12.sp,
              fontWeight = FontWeight.Medium
            )
          }
        }
        Box (
          modifier = Modifier.size(120.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.Black.copy(alpha = 0.3f)),
          contentAlignment = Alignment.Center
        ){
          Text(
            text = "⌚",
            fontSize = 48.sp,
            color = Color.White
          )
        }

      }
    }
  }
}