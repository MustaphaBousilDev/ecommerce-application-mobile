package com.mustapha.application_android_kotlin.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Data class for tab Definition
data class TabItem(
    val title: String,
    val icon: ImageVector,
    val hasNotification: Boolean = false,
    val notificationCount: Int = 0
)

//Tab Definition
object NavigationTabs {
    val tabs = listOf(
        TabItem(
            title = "Home",
            icon = Icons.Default.Home
        ),
        TabItem(
            title = "Search",
            icon = Icons.Default.Search,
        ),
        TabItem(
            title = "Chat",
            icon = Icons.Default.Call,
            hasNotification = true,
            notificationCount = 1
        ),
        TabItem(
            title = "Profile",
            icon = Icons.Default.Person
        ),
        TabItem(
            title = "Setting",
            icon = Icons.Default.Settings
        )
    )
}
//@OptIn is a Kotlin annotation that allows you to use experimental or unstable APIs that the library developers haven't finalized yet.
//🎯 Simple Definition:
//@OptIn = "I understand this feature might change in future versions, but I want to use it anyway"
@OptIn(ExperimentalMaterial3Api::class)
@Composable
//🎯 Simple Definition:
//Modifier = Modifier = "If no modifier is provided, use an empty/default modifier"
fun BottomNavigationBar(
    selectedTab: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier //Modifier = Modifier is a default parameter pattern in Jetpack Compose functions.
){
    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        NavigationTabs.tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    Box(
                        modifier = Modifier.padding(vertical = 4.dp),
                        contentAlignment = Alignment.Center
                    ){
                        // Badge for notifications (red circle with number)
                        if(tab.hasNotification && tab.notificationCount > 0){
                            BadgedBox(
                                badge = {
                                    Badge(
                                        containerColor = Color.Red,
                                        contentColor =  Color.White
                                    ) {
                                        Text(
                                            text = tab.notificationCount.toString(),
                                            fontSize = 10.sp,
                                            fontWeight = FontWeight.Bold,
                                        )
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = tab.icon,
                                    contentDescription = tab.title,
                                    modifier = Modifier.size(24.dp)
                                )
                            }
                        }
                        else {
                            Icon(
                                imageVector = tab.icon,
                                contentDescription = tab.title,
                                modifier = Modifier.size(28.dp)
                            )
                        }
                    }
                },
                label = {
                    Text(
                        text = tab.title,
                        fontSize = 13.sp
                    )
                },
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor =  MaterialTheme.colorScheme.primary,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = MaterialTheme.colorScheme.primaryContainer,

                ),
                modifier = Modifier.padding(vertical = 0.dp)

            )
        }
    }
}