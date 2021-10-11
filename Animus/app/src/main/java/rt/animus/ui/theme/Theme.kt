package rt.animus.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = Pink300,
    primaryVariant = Pink700,
    secondary = Cyan200,
    secondaryVariant = Cyan700
)

private val LightColorPalette = lightColors(
    primary = Pink700,
    primaryVariant = Pink900,
    secondary = Cyan200,
    secondaryVariant = Cyan700
)

@Composable
fun AnimusTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        shapes = Shapes,
        content = content
    )
}