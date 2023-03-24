package com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Diamond
import androidx.compose.material.icons.rounded.Hardware
import androidx.compose.material.icons.rounded.MedicalServices
import androidx.compose.material.icons.rounded.QuestionMark
import androidx.compose.material.icons.rounded.SportsBar
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.WillyWonkaChocolateFactoryTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.theme.providers.AppTheme
import com.guillem.sample_app_willy_wonka_chocolate_factory.commonsui.compose.tooling.ThemePreviews

private const val DEVELOPER = "Developer"
private const val METALWORKER = "Metalworker"
private const val GEMCUTTER = "Gemcutter"
private const val MEDIC = "Medic"
private const val BREWER = "Brewer"


@Composable
fun ProfessionChip(
    profession: String,
) {
    Row(
        modifier = Modifier
            .padding(12.dp)
            .border(border = ButtonDefaults.outlinedButtonBorder, shape = RoundedCornerShape(8.dp))
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        val icon: ImageVector = when (profession) {
            DEVELOPER -> Icons.Rounded.Code
            METALWORKER -> Icons.Rounded.Hardware
            GEMCUTTER -> Icons.Rounded.Diamond
            MEDIC -> Icons.Rounded.MedicalServices
            BREWER -> Icons.Rounded.SportsBar
            else -> Icons.Rounded.QuestionMark
        }
        Icon(
            imageVector = icon,
            tint = AppTheme.colors.defaultTextColor,
            contentDescription = profession
        )
        Spacer(modifier = Modifier.padding(4.dp))
        Text(text = profession, color = AppTheme.colors.defaultTextColor)
    }
}

@ThemePreviews
@Composable
private fun WillyWonkaChocolateFactoryTopBarPreview() {
    WillyWonkaChocolateFactoryTheme {
        Column {
            ProfessionChip(profession = DEVELOPER)
            ProfessionChip(profession = METALWORKER)
            ProfessionChip(profession = GEMCUTTER)
            ProfessionChip(profession = MEDIC)
            ProfessionChip(profession = BREWER)
            ProfessionChip(profession = "Unknown")
        }
    }
}
