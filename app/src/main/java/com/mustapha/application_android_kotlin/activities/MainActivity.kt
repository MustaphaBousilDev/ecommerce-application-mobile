package com.mustapha.application_android_kotlin.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview
import com.mustapha.application_android_kotlin.ui.theme.ApplicationandroidkotlinTheme

class MainActivity : ComponentActivity() {
    //onCreate: Runs when your app starts (like a "setup function")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge(): Makes app use full screen
        enableEdgeToEdge()
        //setContent: Defines what users see on screen
        setContent {
            ApplicationandroidkotlinTheme {
                //Scaffold like a page template that provides basic screen structure
                EcommerceApp()
            }
        }
    }
}

@Composable //this function creates UI elements
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ApplicationandroidkotlinTheme {
        Greeting("Android")
    }
}

@Composable
fun EcommerceApp(){
    //State to track which tab is selected (starts with tab 0= Home)
    var selectedTab by remember { mutableStateOf(0) }
    var tabs = listOf("Home", "Categories", "Cart", "Profile")
    //Like a "page template" that provides basic screen structure
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            //bottom navigation tabs
            TabRow(
                selectedTabIndex = selectedTab,
                containerColor = Color(0xFF6200EE),
                contentColor = Color.White
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        selectedContentColor = Color.White,
                        unselectedContentColor = Color.White.copy(alpha = 0.7f)
                    ) {
                        Text(
                            text = title,
                            fontSize = 12.sp,
                            fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal
                        )
                    }
                }
            }
        }
    ){
        innerPadding ->
        // Content area that changes based on selected tab
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            when (selectedTab) {
                0 -> HomeScreen()
                1 -> CategoriesScreen()
                2 -> CartScreen()
                3 -> ProfileScreen()
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🏠 Welcome to My Store!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Featured Products Coming Soon...",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun CategoriesScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "📂 Product Categories",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Electronics, Clothing, Books...",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun CartScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "🛒 Shopping Cart",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Your cart is empty",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}
@Composable
fun ProfileScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "👤 User Profile",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF6200EE)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Login or Register",
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EcommerceAppPreview() {
    ApplicationandroidkotlinTheme {
        EcommerceApp()
    }
}


