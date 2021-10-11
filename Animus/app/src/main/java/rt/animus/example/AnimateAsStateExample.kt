package rt.animus.example

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import rt.animus.R
import rt.animus.ui.theme.*

@Preview(showBackground = true)
@Composable
fun AnimateAsStatePreview() {
  AnimateAsStateExample()
}

@Composable
fun AnimateAsStateExample() {
  AnimusTheme {
    Column(
      verticalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.padding(top = CONTENT_PADDING_VERTICAL)
    ) {
      var isBw by remember { mutableStateOf(false) }
      var isFullSize by remember { mutableStateOf(true) }
      SettingRow(title = "Black & White", checked = isBw, onCheckedChange = { isBw = it })
      SettingRow(title = "Full Size", checked = isFullSize, onCheckedChange = { isFullSize = it })
      FoodCard(bw = isBw, fullSize = isFullSize)
    }
  }
}

@Composable
fun FoodCard(bw: Boolean, fullSize: Boolean) {

  //region === animate*AsState ===

  val saturation by animateFloatAsState(
    targetValue = if (bw) 0f else 1f,
    animationSpec = tween(2000)
  )

  val width by animateDpAsState(
    targetValue = if (fullSize) FOOD_WIDTH_MAX else FOOD_WIDTH_MIN,
    animationSpec = spring(
      dampingRatio = Spring.DampingRatioLowBouncy,
      stiffness = Spring.StiffnessLow
    )
  )

  //endregion

  Card(
    modifier = Modifier.padding(horizontal = CONTENT_PADDING_HORIZONTAL, vertical = CONTENT_PADDING_VERTICAL),
    elevation = EXAMPLE_CARD_ELEVATION
  ) {
    Image(
      painterResource(id = R.drawable.loes_klinker_gpzixog6xlg_unsplash),
      contentDescription = "thit nuong photo",
      contentScale = ContentScale.FillWidth,
      colorFilter = ColorFilter.colorMatrix(ColorMatrix().apply { this.setToSaturation(saturation) }),
      modifier = Modifier
        .width(width)
        .aspectRatio(1f)
    )
  }
}


@Composable
fun SettingRow(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
  Row(
    modifier = Modifier
      .padding(horizontal = CONTENT_PADDING_HORIZONTAL, vertical = FOOD_SETTING_PADDING_VERTICAL)
      .fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly
  ) {
    Text(text = title, modifier = Modifier.weight(1f))
    Switch(checked = checked, onCheckedChange = onCheckedChange)
  }
}