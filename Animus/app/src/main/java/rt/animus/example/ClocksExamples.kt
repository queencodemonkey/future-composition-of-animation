package rt.animus.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import rt.animus.common.ExampleDisplay
import rt.animus.common.ExampleSpacer
import rt.animus.example.views.ClockView
import rt.animus.ui.theme.AnimusTheme
import rt.animus.R
import rt.animus.example.composeables.TimescaledClock
import rt.animus.ui.theme.CONTENT_PADDING_HORIZONTAL

@Preview
@Composable
fun LowLeveApiPreview() {
  LowLeveApiExample()
}

@Composable
fun LowLeveApiExample() {
  AnimusTheme {
    Column(
      modifier = Modifier
        .background(MaterialTheme.colors.background)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
      ExampleSpacer()
      ViewCanvasDisplay()
      ExampleSpacer()
      ComposeCanvasDisplay()
      ExampleSpacer()
    }
  }
}

@Composable
private fun ViewCanvasDisplay() {
  ExampleDisplay(title = "View Canvas", collapsible = true) {
    AndroidView(
      factory = { context ->
        ClockView(context)
      },
      modifier = Modifier
        .align(Alignment.Center)
        .padding(dimensionResource(id = R.dimen.content_padding_vertical))
        .size(dimensionResource(id = R.dimen.clock_size))
    )

  }
}

@Composable
private fun ComposeCanvasDisplay() {
  ExampleDisplay(title = "Compose Canvas") {
    Column(
      modifier = Modifier.fillMaxWidth(),
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      var sliderPosition by remember { mutableStateOf(1.0f) }
      var timescale by remember { mutableStateOf(1.0f) }
      var showSlider by remember { mutableStateOf(true) }

      TimescaledClock(timescale = timescale, onDone = { showSlider = false })

      AnimatedVisibility(visible = showSlider) {
        Spacer(modifier = Modifier.height(16.dp))
        Slider(
          value = sliderPosition,
          onValueChange = { sliderPosition = it },
          modifier = Modifier
            .padding(horizontal = CONTENT_PADDING_HORIZONTAL)
            .fillMaxWidth(),
          valueRange = (1f..8f),
          steps = 8,
          onValueChangeFinished = { timescale = sliderPosition }
        )
      }
    }
  }
}