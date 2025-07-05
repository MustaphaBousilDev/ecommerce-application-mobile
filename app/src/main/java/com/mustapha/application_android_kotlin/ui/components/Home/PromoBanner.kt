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
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.Immutable

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
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
@Immutable
data class PromoItem(
  val id: String,
  val discount: String,
  val title: String,
  val buttonText: String,
  val imageRes: Int,
  val discountColor: Color = Color(0xFFFF6B35),
  val widthImg: Int,
)

//✅ OPTIMIZATION 2: Create constants to avoid recreation
private object PromoBannerConstants {
  val cardShape = RoundedCornerShape(
    topStart = 0.dp,
    topEnd = 0.dp,
    bottomEnd = 70.dp,
    bottomStart = 70.dp
  )
  val backgroundGradient = Brush.horizontalGradient(
    colors = listOf(Color(0xFF424242), Color(0xFF212121))
  )
  const val  AUTO_SCROLL_DELAY = 3000L;
  val promoItems = listOf(
    PromoItem(
      id = "promo_1",
      discount = "50%",
      title = "Smart Shopping",
      buttonText = "Shop",
      imageRes = R.drawable.ee,
      widthImg = 300
    ),
    PromoItem(
      id = "promo_2",
      discount = "30%",
      title = "Best Deals of the Week",
      buttonText = "Explore",
      imageRes = R.drawable.ee2,
      discountColor = Color(0xFF4CAF50),
      widthImg = 280
    ),
    PromoItem(
      id = "promo_3",
      discount = "70%",
      title = "Limited Time Mega Sale",
      buttonText = "Buy Now",
      imageRes = R.drawable.ee3,
      discountColor = Color(0xFF9C27B0),
      widthImg = 280
    ),
    PromoItem(
      id = "promo_4",
      discount = "70%",
      title = "Limited Time Mega Sale",
      buttonText = "Buy Now",
      imageRes = R.drawable.ee4,
      discountColor = Color(0xFF9C27B0),
      widthImg = 320
    )
  )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PromoBanner(
  promoItems: List<PromoItem> = PromoBannerConstants.promoItems,
  onPromoClick: (PromoItem) -> Unit = {},
  onFavoriteClick: () -> Unit = {},
  onNotificationClick: () -> Unit = {},
  autoScrolledEnabled: Boolean = true,
) {

  // Pager state for managing the carousel
  val pagerState = rememberPagerState(
    initialPage = 0,
    pageCount = { promoItems.size }
  )

  // Auto-scroll effect
  LaunchedEffect(pagerState, autoScrolledEnabled, promoItems.size) {
    if (autoScrolledEnabled && promoItems.size > 1) {
      while (true) {
        delay(2000)
        val nextPage = (pagerState.currentPage + 1) % promoItems.size
        pagerState.animateScrollToPage(nextPage)
      }
    }
  }
  val cardModifier = remember {
    Modifier.fillMaxWidth().height(300.dp)
  }

  Card(
    modifier = cardModifier,
    shape = PromoBannerConstants.cardShape,
    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
  ) {
    Box(
      modifier = Modifier.fillMaxSize().background(
        brush = PromoBannerConstants.backgroundGradient
      )
    ) {
      PromoHeader(
        onFavoriteClick = onFavoriteClick,
        onNotificationClick = onNotificationClick
      )

      // Sliding promo content
      CompositionLocalProvider (LocalOverscrollConfiguration provides null) {
        HorizontalPager(
          state = pagerState,
          modifier = Modifier.fillMaxSize().padding(20.dp),
          key = {page -> promoItems[page].id } //stable key for better performance
        ) { page ->
          PromoPageContent(
            promo =  promoItems[page],
            onPromoClick = onPromoClick
          )
        }
      }


      // Optional: Add page indicators (dots)
      PromoPageIndicators(
        currentPage = pagerState.currentPage,
        totalPages = promoItems.size,
        modifier = Modifier.align(Alignment.BottomCenter)
          .padding(bottom = 16.dp)
      )
    }
  }
}



@Composable
private fun PromoHeader(
  onFavoriteClick: () -> Unit,
  onNotificationClick: () -> Unit
) {
  Row(
    modifier = Modifier.fillMaxWidth().padding(16.dp),
    horizontalArrangement = Arrangement.SpaceBetween,
    verticalAlignment = Alignment.Top
  ){
     Row (verticalAlignment = Alignment.CenterVertically){
       Icon(
         imageVector = Icons.Default.ShoppingCart,
         contentDescription = "ShopeEase Logo",
         tint = Color.White,
         modifier = Modifier.size(24.dp)
       )
       Spacer(modifier = Modifier.width(8.dp))
       Text(
         text="ShopEase",
         color  =Color.White,
         fontSize = 20.sp,
         fontWeight = FontWeight.Bold
       )
     }
     Row (verticalAlignment = Alignment.CenterVertically){
       IconButton(onClick = onFavoriteClick) {
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
             contentColor = Color.White,
           ) {
             Text(text="2", fontSize = 10.sp)
           }
         }
       ) {
         IconButton(onClick = onNotificationClick) {
           Icon(
             imageVector = Icons.Default.Notifications,
             tint = Color.White,
             contentDescription = "Notifications",
             modifier = Modifier.size(24.dp)
           )
         }
       }
     }
  }
}

@Composable
private fun PromoPageContent(
  promo: PromoItem,
  onPromoClick: (PromoItem) -> Unit
) {
  Box(modifier = Modifier.fillMaxSize()){
    // ✅ OPTIMIZATION: optimize image with stable modifier
    val imageModifier = remember(promo.widthImg) {
      Modifier.height(300.dp)
        .width(promo.widthImg.dp)
        .alpha(0.3f)
    }
    Image(
      painter = painterResource(id = promo.imageRes),
      contentDescription = "Promo background for ${promo.title}",
      contentScale = ContentScale.Crop,
      modifier = imageModifier
    )
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
          text = promo.discount,
          color = promo.discountColor,
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
        text = promo.title,
        color = Color.White,
        fontSize = 16.sp,
        fontWeight = FontWeight.Medium
      )

      Spacer(modifier = Modifier.height(12.dp))

      // ✅ OPTIMIZATION 12: Stable button with remember
      PromoButton(
        text = promo.buttonText,
        color = promo.discountColor,
        onClick = { onPromoClick(promo) }
      )
    }
  }
}

@Composable
private fun PromoButton(
  text: String,
  color: Color,
  onClick: () -> Unit
) {
  val buttonModifier = remember {
    Modifier
      .height(36.dp)
      .width(110.dp)
  }

  Button(
    onClick = onClick,
    colors = ButtonDefaults.buttonColors(containerColor = color),
    shape = RoundedCornerShape(20.dp),
    modifier = buttonModifier
  ) {
    Text(
      text = text,
      color = Color.White,
      fontSize = 12.sp,
      fontWeight = FontWeight.Bold,
    )
  }
}

@Composable
private fun PromoPageIndicators(
  currentPage: Int,
  totalPages: Int,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier,
    horizontalArrangement = Arrangement.spacedBy(4.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {
    repeat(totalPages) { index ->
      val isSelected = currentPage == index

      // ✅ Remember indicator modifier for performance
      val indicatorModifier = remember(isSelected) {
        Modifier
          .width(if (isSelected) 15.dp else 5.dp)
          .height(5.dp)
          .background(
            color = if (isSelected) Color.White else Color.White.copy(alpha = 0.5f),
            shape = RoundedCornerShape(50)
          )
      }

      Box(modifier = indicatorModifier)
    }
  }
}
