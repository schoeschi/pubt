package ch.thruut.pubt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import ch.thruut.pubt.R

val googleSansFlex = FontFamily(
    Font(
        resId = R.font.google_sans_flex,
        weight = FontWeight.Normal,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(400)
        )
    ),
    Font(
        resId = R.font.google_sans_flex,
        weight = FontWeight.Medium,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(500)
        )
    ),
    Font(
        resId = R.font.google_sans_flex,
        weight = FontWeight.Bold,
        variationSettings = FontVariation.Settings(
            FontVariation.weight(700)
        )
    )
)

private val defaultTypography = Typography()

val Typography = Typography(
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = googleSansFlex),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = googleSansFlex),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = googleSansFlex),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = googleSansFlex),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = googleSansFlex),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = googleSansFlex),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = googleSansFlex),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = googleSansFlex),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = googleSansFlex),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = googleSansFlex),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = googleSansFlex),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = googleSansFlex),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = googleSansFlex),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = googleSansFlex),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = googleSansFlex)
)