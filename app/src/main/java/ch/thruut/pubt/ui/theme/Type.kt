package ch.thruut.pubt.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation
import androidx.compose.ui.text.font.FontWeight
import ch.thruut.pubt.R

@OptIn(ExperimentalTextApi::class)
val GoogleSansFlex = FontFamily(
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
    displayLarge = defaultTypography.displayLarge.copy(fontFamily = GoogleSansFlex),
    displayMedium = defaultTypography.displayMedium.copy(fontFamily = GoogleSansFlex),
    displaySmall = defaultTypography.displaySmall.copy(fontFamily = GoogleSansFlex),
    headlineLarge = defaultTypography.headlineLarge.copy(fontFamily = GoogleSansFlex),
    headlineMedium = defaultTypography.headlineMedium.copy(fontFamily = GoogleSansFlex),
    headlineSmall = defaultTypography.headlineSmall.copy(fontFamily = GoogleSansFlex),
    titleLarge = defaultTypography.titleLarge.copy(fontFamily = GoogleSansFlex),
    titleMedium = defaultTypography.titleMedium.copy(fontFamily = GoogleSansFlex),
    titleSmall = defaultTypography.titleSmall.copy(fontFamily = GoogleSansFlex),
    bodyLarge = defaultTypography.bodyLarge.copy(fontFamily = GoogleSansFlex),
    bodyMedium = defaultTypography.bodyMedium.copy(fontFamily = GoogleSansFlex),
    bodySmall = defaultTypography.bodySmall.copy(fontFamily = GoogleSansFlex),
    labelLarge = defaultTypography.labelLarge.copy(fontFamily = GoogleSansFlex),
    labelMedium = defaultTypography.labelMedium.copy(fontFamily = GoogleSansFlex),
    labelSmall = defaultTypography.labelSmall.copy(fontFamily = GoogleSansFlex)
)