package com.nicholas.rutherford.wag.trail.compose.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Sage,
    onPrimary = Ink,
    primaryContainer = NightGreen,
    onPrimaryContainer = Sage,
    inversePrimary = Green,
    secondary = Moss,
    onSecondary = Night,
    secondaryContainer = NightHigh,
    onSecondaryContainer = Paper,
    tertiary = LeafLight,
    onTertiary = Night,
    tertiaryContainer = NightGreen,
    onTertiaryContainer = LeafLight,
    background = Night,
    onBackground = Paper,
    surface = NightRaised,
    onSurface = Paper,
    surfaceVariant = NightHigh,
    onSurfaceVariant = Moss,
    surfaceTint = Night,
    inverseSurface = Paper,
    inverseOnSurface = Ink,
    outline = LineNight,
    outlineVariant = LineNightSoft,
    surfaceBright = NightHighest,
    surfaceDim = Night,
    surfaceContainer = NightRaised,
    surfaceContainerHigh = NightHigh,
    surfaceContainerHighest = NightHighest,
    surfaceContainerLow = NightLow,
    surfaceContainerLowest = Night
)

private val LightColorScheme = lightColorScheme(
    primary = Green,
    onPrimary = Cream,
    primaryContainer = GreenWash,
    onPrimaryContainer = Green,
    inversePrimary = Sage,
    secondary = Stone,
    onSecondary = Paper,
    secondaryContainer = StoneWash,
    onSecondaryContainer = Ink,
    tertiary = Leaf,
    onTertiary = Cream,
    tertiaryContainer = LeafWash,
    onTertiaryContainer = Green,
    background = Paper,
    onBackground = Ink,
    surface = Paper,
    onSurface = Ink,
    surfaceVariant = PaperDim,
    onSurfaceVariant = Muted,
    surfaceTint = Paper,
    inverseSurface = Night,
    inverseOnSurface = Paper,
    outline = Line,
    outlineVariant = LineSoft,
    surfaceBright = PaperBright,
    surfaceDim = PaperDeep,
    surfaceContainer = PaperDim,
    surfaceContainerHigh = PaperDeep,
    surfaceContainerHighest = LineSoft,
    surfaceContainerLow = Paper,
    surfaceContainerLowest = PaperBright
)

/**
 * Default Theme used to detect the color scheme style across the application
 * based on factors such as dark theme, and dynamic color and supported version
 *
 * @param darkTheme Determines if darkTheme is enabled defaults to [isSystemInDarkTheme]
 * @param dynamicColor Used to check if dynamic is enabled or not
 * @param content [Composable] content thats passed into [MaterialTheme]
 */
@Composable
fun WagTrailTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) {
                dynamicDarkColorScheme(context = context)
            } else {
                dynamicLightColorScheme(context = context)
            }
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
