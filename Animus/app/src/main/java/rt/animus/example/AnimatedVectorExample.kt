package rt.animus.example

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.annotation.DrawableRes
import androidx.compose.animation.graphics.ExperimentalAnimationGraphicsApi
import androidx.compose.animation.graphics.res.animatedVectorResource
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import rt.animus.common.ExampleSpacer
import rt.animus.R
import rt.animus.common.ExampleDisplay
import rt.animus.ui.theme.AnimusTheme

@Preview
@Composable
fun AnimatedVectorPreview() {
  AnimatedVectorExample()
}

@Composable
fun AnimatedVectorExample() {
  AnimusTheme {
    Column(
      modifier = Modifier
        .background(MaterialTheme.colors.background)
        .fillMaxSize()
        .verticalScroll(rememberScrollState())
    ) {
      ExampleSpacer()
      AndroidViewDisplay()
      ExampleSpacer()
      ComposeDisplay()
      ExampleSpacer()
    }
  }
}

@SuppressLint("InflateParams")
@Composable
private fun AndroidViewDisplay() {
  ExampleDisplay(title = "ImageView") {
    AndroidView(
      factory = { context ->
        LayoutInflater.from(context)
          .inflate(R.layout.view_vector_drawable, null, false)
      },
      modifier = Modifier.align(Alignment.Center)
    )
  }
}

@Composable
private fun ComposeDisplay() {
  ExampleDisplay(title = "Compose Icon") {
    Column(
      modifier = Modifier
        .align(Alignment.Center)
        .padding(
          vertical = dimensionResource(id = R.dimen.content_padding_vertical)
        ),
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      val vectors = listOf(
        R.drawable.anim_board_to_card,
        R.drawable.anim_clock,
        R.drawable.anim_checked
      )
      vectors.forEachIndexed { index, vectorResId ->
        AnimatedVectorDisplay(vectorResId = vectorResId)
        if (index != vectors.lastIndex) {
          Spacer(
            modifier = Modifier.height(dimensionResource(id = R.dimen.vector_drawable_layout_gap))
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalAnimationGraphicsApi::class)
@Composable
private fun AnimatedVectorDisplay(@DrawableRes vectorResId: Int) {
  val vector = animatedVectorResource(id = vectorResId)
  var atEnd by remember { mutableStateOf(false) }
  Icon(
    painter = vector.painterFor(atEnd = atEnd),
    contentDescription = "",
    modifier = Modifier
      .size(dimensionResource(id = R.dimen.vector_drawable_size))
      .clickable { atEnd = !atEnd })
}