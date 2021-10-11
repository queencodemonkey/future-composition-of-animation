package rt.animus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import rt.animus.launch.ExampleLaunchScreen
import rt.animus.ui.theme.AnimusTheme

//region === Activity ===

class MainActivity : ComponentActivity() {
  @OptIn(ExperimentalAnimationApi::class)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      AnimusTheme {
        val systemUiController = rememberSystemUiController()
        val isLight = MaterialTheme.colors.isLight
        val statusBarColor = if (isLight) MaterialTheme.colors.primaryVariant else MaterialTheme.colors.background
        SideEffect {
          // Update all of the system bar colors to be transparent, and use
          // dark icons if we're in light theme

          systemUiController.setStatusBarColor(
            color = statusBarColor,
            darkIcons = !isLight
          )
          // setStatusBarsColor() and setNavigationBarsColor() also exist
        }

        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          ExampleLaunchScreen()
        }
      }
    }
  }
}

//endregion