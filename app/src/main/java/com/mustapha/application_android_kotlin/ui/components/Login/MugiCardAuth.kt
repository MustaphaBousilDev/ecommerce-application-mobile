package com.mustapha.application_android_kotlin.ui.components.Login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mustapha.application_android_kotlin.ui.components.cards.MugiCard
import com.mustapha.application_android_kotlin.ui.components.cards.MugiCardVariant
import com.mustapha.application_android_kotlin.ui.theme.MugiColors
import com.mustapha.application_android_kotlin.ui.theme.MugiSpacing

@Composable
fun MugiAuthCard(
    modifier: Modifier = Modifier,
    title: String = "",
    subtitle: String = "",
    showDivider: Boolean = false,
    content: @Composable ColumnScope.() -> Unit
) {
    MugiCard (
        modifier = modifier,
        variant = MugiCardVariant.Elevated,
        elevation = 0.dp, // Higher elevation for auth
        shape = RoundedCornerShape(35.dp) // More rounded for modern look
    ) {
        // Header section
        if (title.isNotEmpty() || subtitle.isNotEmpty()) {
            Column {
                if (title.isNotEmpty()) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.headlineSmall,
                        color = MugiColors.Neutral900,
                        textAlign = TextAlign.Center,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold

                    )
                }

                if (subtitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(MugiSpacing.lg))
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MugiColors.Neutral500
                    )
                }

                if (showDivider) {
                    Spacer(modifier = Modifier.height(MugiSpacing.lg))
                    Divider(color = MugiColors.Neutral100)
                    Spacer(modifier = Modifier.height(MugiSpacing.lg))
                }
            }
        }

        // Content
        content()
    }
}