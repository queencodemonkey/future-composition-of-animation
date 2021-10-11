package rt.animus.example

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import rt.animus.R
import rt.animus.ui.theme.AnimusTheme
import rt.animus.ui.theme.CONTENT_PADDING_HORIZONTAL
import rt.animus.ui.theme.CONTENT_PADDING_VERTICAL
import rt.animus.ui.theme.EXAMPLE_CARD_ELEVATION

@Preview(showBackground = true)
@Composable
fun AnimatedVisibilityPreview() {
  AnimatedVisibilityExample()
}

@Composable
fun AnimatedVisibilityExample() {
  AnimusTheme {
    Column(verticalArrangement = Arrangement.SpaceEvenly) {
      var shown by remember { mutableStateOf(true) }
      Row(
        modifier = Modifier
          .padding(horizontal = CONTENT_PADDING_HORIZONTAL, vertical = CONTENT_PADDING_VERTICAL)
          .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
      ) {
        Text(text = "Shown", modifier = Modifier.weight(1f))
        Switch(checked = shown, onCheckedChange = { shown = it })
      }
//      AnimatedVisibility(visible = shown) {
//        Card(
//          modifier = Modifier
//            .padding(horizontal = CONTENT_PADDING_HORIZONTAL, vertical = CONTENT_PADDING_VERTICAL)
//            .fillMaxWidth(),
//          elevation = EXAMPLE_CARD_ELEVATION,
//        ) {
//          Image(
//            painterResource(id = R.drawable.mae_mu_rxdcb_bmgdg_unsplash),
//            contentDescription = "crab photo",
//            contentScale = ContentScale.FillWidth
//          )
//        }
//      }

      //region === Alternatively… ===
      VisibilityCrabCard(shown = shown)
      //endregion
    }
  }
}

@Composable
fun VisibilityCrabCard(shown: Boolean) {
  AnimatedVisibility(
    visible = shown,
    enter = slideInVertically(initialOffsetY = { it / 2 }) +
        fadeIn(animationSpec = keyframes {
          durationMillis = 500
          0f at 150
        }),
    exit = slideOutVertically(targetOffsetY = { it / 2 }) +
        fadeOut()

  ) {
    Card(
      modifier = Modifier
        .padding(horizontal = CONTENT_PADDING_HORIZONTAL, vertical = CONTENT_PADDING_VERTICAL)
        .fillMaxWidth(),
      elevation = EXAMPLE_CARD_ELEVATION
    ) {
      Image(
        painterResource(id = R.drawable.mae_mu_rxdcb_bmgdg_unsplash),
        contentDescription = "crab photo",
        contentScale = ContentScale.FillWidth
      )
    }
  }
}