package com.mustapha.application_android_kotlin.ui.screens.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
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
import com.mustapha.application_android_kotlin.ui.components.Login.MugiAuthCard
import com.mustapha.application_android_kotlin.ui.components.inputs.MugiEmailField
import com.mustapha.application_android_kotlin.ui.components.inputs.MugiPasswordField
import com.mustapha.application_android_kotlin.ui.components.layouts.MugiContainer
import com.mustapha.application_android_kotlin.ui.components.layouts.MugiScaffold
import com.mustapha.application_android_kotlin.ui.theme.MugiColors
import com.mustapha.application_android_kotlin.ui.theme.MugiSpacing


@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit = {},
    onNavigateToRegister: () -> Unit = {},
    onNavigateToForgotPassword: () -> Unit = {}
  ){
    var email =""
    var password=""
   MugiScaffold { paddingValues ->
       MugiContainer(modifier = Modifier.padding(paddingValues)) {
           Text(
               text = "SignIn",
               style = MaterialTheme.typography.headlineMedium,
               color = MugiColors.Neutral900
           )
           Spacer(modifier = Modifier.height(MugiSpacing.xl))
           MugiAuthCard(
               title = "Welcome Back",
               subtitle = "Sign in to continue shopping",
               showDivider = true
           ) {
               MugiEmailField(
                   value = "er",
                   onValueChange = { email = it }
               )
               Spacer(modifier = Modifier.height(16.dp))
               MugiPasswordField(
                   value="",
                   onValueChange = { password = it }

               )
               Spacer(modifier = Modifier.height(24.dp))
               Button(
                   onClick = onLoginSuccess,
                   modifier = Modifier.fillMaxWidth()
               ) {
                   Text("Login")
               }
           }
       }
   }
}