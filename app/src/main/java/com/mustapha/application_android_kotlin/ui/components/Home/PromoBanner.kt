package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
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
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
import androidx.compose.runtime.CompositionLocalProvider

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import com.mustapha.application_android_kotlin.R
import kotlinx.coroutines.delay

// Data class for promo items
data class PromoItem(
  val discount: String,
  val title: String,
  val buttonText: String,
  val imageRes: Int,
  val discountColor: Color = Color(0xFFFF6B35),
  val widthImg: Int,
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromoBanner() {
  // Sample promo data - you can replace with your own
  val promoItems = listOf(
    PromoItem(
      discount = "50%",
      title = "Smart Shopping",
      buttonText = "Shop",
      imageRes = R.drawable.ee,
      widthImg= 300
    ),
    PromoItem(
      discount = "30%",
      title = "Best Deals of the Week",
      buttonText = "Explore",
      imageRes = R.drawable.ee2, // Replace with different images
      discountColor = Color(0xFF4CAF50),
      widthImg= 280
    ),
    PromoItem(
      discount = "70%",
      title = "Limited Time Mega Sale",
      buttonText = "Buy Now",
      imageRes = R.drawable.ee3, // Replace with different images
      discountColor = Color(0xFF9C27B0),
      widthImg= 280

    ),
    PromoItem(
      discount = "70%",
      title = "Limited Time Mega Sale",
      buttonText = "Buy Now",
      imageRes = R.drawable.ee4, // Replace with different images
      discountColor = Color(0xFF9C27B0),
      widthImg= 320
    ),

  )

  // Pager state for managing the carousel
  val pagerState = rememberPagerState(
    initialPage = 0,
    pageCount = { promoItems.size }
  )

  // Auto-scroll effect
  LaunchedEffect(pagerState) {
    while (true) {
      delay(3000) // 3 seconds delay
      val nextPage = (pagerState.currentPage + 1) % promoItems.size
      pagerState.animateScrollToPage(nextPage)
    }
  }

  Card(
    modifier = Modifier.fillMaxSize().height(300.dp),
    shape = RoundedCornerShape(
      topStart = 0.dp,
      topEnd = 0.dp,
      bottomStart = 50.dp,
      bottomEnd = 50.dp
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxSize().background(
        brush = Brush.horizontalGradient(
          colors = listOf(Color(0xFF424242), Color(0xFF212121))
        )
      )
    ) {
      // Top bar with logo and icons
      Row(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Icon(
            imageVector = Icons.Default.ShoppingCart,
            contentDescription = "ShopEase Logo",
            tint = Color.White,
            modifier = Modifier.size(24.dp)
          )
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "ShopEase",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
          )
        }
        Row {
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
                Text(text = "2", fontSize = 10.sp)
              }
            }
          ) {
            IconButton(onClick = {}) {
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

      // Sliding promo content
      CompositionLocalProvider (LocalOverscrollConfiguration provides null) {
        HorizontalPager(
          state = pagerState,
          modifier = Modifier.fillMaxSize().padding(20.dp),
        ) { page ->
          val currentPromo = promoItems[page]

          Box(modifier = Modifier.fillMaxSize()) {
            // Background image
            Image(
              painter = painterResource(id = currentPromo.imageRes),
              contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier
                .height(300.dp)
                .width(currentPromo.widthImg.dp)
                .alpha(0.3f)
            )

            // Content overlay
            Column(
              modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp),
              verticalArrangement = Arrangement.Center
            ) {
              // Discount section
              Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = currentPromo.discount,
                  color = currentPromo.discountColor,
                  fontSize = 36.sp,
                  fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.width(12.dp))
                Text(
                  text = "OFF",
                  color = Color.Gray,
                  fontSize = 16.sp,
                  fontWeight = FontWeight.Bold
                )
              }

              // Title
              Text(
                text = currentPromo.title,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
              )
              Spacer(modifier = Modifier.height(12.dp))
              // Button
              Row(
                modifier = Modifier.width(400.dp).height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
              ) {
                Button(
                  onClick = {
                    // Handle button click for each promo
                    // You can add different actions based on the current page
                  },
                  colors = ButtonDefaults.buttonColors(
                    containerColor = currentPromo.discountColor
                  ),
                  shape = RoundedCornerShape(20.dp),
                  modifier = Modifier
                    .height(36.dp)
                    .width(110.dp)
                ) {
                  Text(
                    text = currentPromo.buttonText,
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                  )
                }
              }
            }
          }
        }
      }


      // Optional: Add page indicators (dots)
      Row(
        modifier = Modifier
          .align(Alignment.BottomCenter)
          .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalAlignment = Alignment.CenterVertically
      ) {
        repeat(promoItems.size) { index ->
          val isSelected = pagerState.currentPage == index
          Box(
            modifier = Modifier
              .width(if (isSelected) 15.dp else 5.dp)
              .height(5.dp)
              .background(
                color = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
                shape = RoundedCornerShape(50)
              )
          )
        }
      }
    }
  }
}