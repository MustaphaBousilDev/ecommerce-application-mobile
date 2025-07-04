package com.mustapha.application_android_kotlin.ui.components.Home

import androidx.compose.foundation.lazy.items

import android.media.Image
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
            name="",
            logo = R.drawable.cat1
        ),
        CategoryItem(
            id=2,
            name="",
            logo = R.drawable.cat2
        ),
        CategoryItem(
            id=3,
            name="",
            logo = R.drawable.cat3
        ),
        CategoryItem(
            id=4,
            name="",
            logo = R.drawable.cat4
        ),CategoryItem(
            id=5,
            name="",
            logo = R.drawable.cat5
        ),
        CategoryItem(
            id=6,
            name="",
            logo = R.drawable.cat6
        ),
        CategoryItem(
            id=6,
            name="",
            logo = R.drawable.cat7
        )
    )

    LazyRow (
        modifier = Modifier.fillMaxSize().height(70.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp),

    ){
        items (categoryItem) {currentCategory ->
            CategoryCard(category = currentCategory)

        }
    }


}


@Composable
fun CategoryCard(category: CategoryItem){
    Column(

        modifier = Modifier.width(70.dp)
    ) {
        //Circular Image with Border
        Box(
            modifier = Modifier.size(70.dp)
                .clip(CircleShape)
                .border(
                    width = 3.dp,
                    color = Color(0xFF6C3FF),
                    shape = CircleShape
                ).background(color = Color.White, // Light background
                    shape = CircleShape)
                .padding(5.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = category.logo),
                contentDescription = category.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = category.name,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF2D3748), // Dark gray
                textAlign = TextAlign.Center,
                maxLines = 1,

            )
        }
    }
}
