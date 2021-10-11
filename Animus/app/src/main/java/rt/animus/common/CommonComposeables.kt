package rt.animus.common

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import rt.animus.ui.theme.CONTENT_PADDING_HORIZONTAL
import rt.animus.ui.theme.CONTENT_PADDING_VERTICAL
import rt.animus.ui.theme.EXAMPLE_CARD_ELEVATION
import rt.animus.ui.theme.EXAMPLE_CARD_PADDING

@Composable
fun ExampleDisplayTitle(title: String, modifier: Modifier = Modifier) {
  Text(
    text = title,
    style = MaterialTheme.typography.subtitle2,
    modifier = modifier
  )
}

@Composable
fun ExampleDisplayCard(
  alpha: Float = 1f,
  elevation: Dp = EXAMPLE_CARD_ELEVATION,
  content: @Composable BoxScope.() -> Unit
) {
  Card(
    backgroundColor = MaterialTheme.colors.surface,
    elevation = elevation,
    modifier = Modifier
      .fillMaxWidth()
      .padding(EXAMPLE_CARD_PADDING)
  ) {
    Box(
      modifier = Modifier
        .fillMaxWidth()
        .alpha(alpha)
    ) {
      content()
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ExampleDisplay(title: String, collapsible: Boolean = false, content: @Composable BoxScope.() -> Unit) {
  // Display collapsible example card.
  if (collapsible) {
    // State that manages the overall transition. In this case,
    // we're launching a transition when the example is collapsed or expanded.
    var cardShown by remember { mutableStateOf(true) }

    val transition = updateTransition(
      targetState = cardShown,
      label = "Toggle $title display"
    )
    val contentAlpha by transition.animateFloat(
      label = "fade in/out card content",
      transitionSpec = { tween(350, delayMillis = if (this.initialState) 0 else 150) }
    ) { shown -> if (shown) 1f else 0f }

    val cardElevation by transition.animateDp(
      label = "raise/lower card elevation",
      transitionSpec = { tween(350, delayMillis = if (this.initialState) 150 else 0) }
    ) { shown -> if (shown) EXAMPLE_CARD_ELEVATION else 0.dp }

    Row(
      horizontalArrangement = Arrangement.SpaceEvenly,
      modifier = Modifier.padding(horizontal = CONTENT_PADDING_HORIZONTAL)
    ) {
      ExampleDisplayTitle(
        title = title,
        modifier = Modifier.weight(1f)
      )
      Switch(checked = cardShown, onCheckedChange = { cardShown = it })
    }
    transition.AnimatedVisibility(
      visible = { it },
      enter = expandHorizontally(expandFrom = Alignment.CenterHorizontally),
      exit = shrinkVertically(animationSpec = tween(350, 350))
    ) {
      ExampleDisplayCard(alpha = contentAlpha, elevation = cardElevation, content = content)
    }

    // Display non-collapsible example card.
  } else {
    ExampleDisplayTitle(
      title = title,
      modifier = Modifier.padding(horizontal = CONTENT_PADDING_HORIZONTAL)
    )
    ExampleDisplayCard(content = content)
  }
}

@Composable
fun ExampleSpacer() = Spacer(modifier = Modifier.height(CONTENT_PADDING_VERTICAL))