package com.mustapha.application_android_kotlin.ui.components.Home

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustapha.application_android_kotlin.R

data class CategoryItem(
    val id: Number,
    val name: String,
    val logo: Int

)

@OptIn(ExperimentalFoundationApi::class)
@Composable()
fun CategoryTabs() {
    val categoryItem = listOf(
        CategoryItem(
            id=1,
            name="technologies",
            logo = R.drawable.promo
        ),
        CategoryItem(
            id=2,
            name="clothes",
            logo = R.drawable.promo
        ),
        CategoryItem(
            id=3,
            name="house",
            logo = R.drawable.promo
        ),
        CategoryItem(
            id=4,
            name="baby",
            logo = R.drawable.promo
        ),CategoryItem(
            id=5,
            name="sports",
            logo = R.drawable.promo
        ),
        CategoryItem(
            id=6,
            name="games",
            logo = R.drawable.promo
        )
    )

    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { categoryItem.size}
    )
    CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier.height(50.dp),
            pageSpacing = 15.dp,
            contentPadding = PaddingValues(horizontal = 40.dp)

        ) {
            page ->
            val currentPromo = categoryItem[page]
            Box(
                modifier = Modifier.fillMaxSize()
                    .background(Color.Cyan),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text=currentPromo.name,
                    fontWeight = FontWeight.Bold,
                    color = Color.DarkGray,
                    fontSize = 25.sp
                )
            }
        }
    }
}