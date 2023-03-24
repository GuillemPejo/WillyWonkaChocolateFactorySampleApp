package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.providers.AppTheme

private const val MAX_LINES_VALUE = 3

@Composable
fun ExpandableText(
    modifier: Modifier = Modifier,
    text: String,
    maxLine: Int = MAX_LINES_VALUE,
) {
    var isDescriptionExpanded by remember { mutableStateOf(false) }
    var isExpandedNeeded by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        Text(
            text = text,
            style = AppTheme.typography.bodyLarge,
            color = AppTheme.colors.onSurface,
            textAlign = TextAlign.Justify,
            onTextLayout = { textLayoutResult ->
                isExpandedNeeded = textLayoutResult.hasVisualOverflow
            },
            maxLines = if (isDescriptionExpanded) Int.MAX_VALUE else maxLine,
            overflow = if (isDescriptionExpanded) {
                TextOverflow.Visible
            } else TextOverflow.Ellipsis
        )
        if (isExpandedNeeded || isDescriptionExpanded) {
            Text(
                text = if (isDescriptionExpanded) "See less" else "See more",
                modifier = Modifier.clickable(
                    enabled = true,
                    onClick = { isDescriptionExpanded = !isDescriptionExpanded })
                    .padding(top = 4.dp),
                style = AppTheme.typography.bodyLarge,
                color = AppTheme.colors.primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}