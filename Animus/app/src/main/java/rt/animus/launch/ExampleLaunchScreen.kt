package rt.animus.launch

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import rt.animus.R
import rt.animus.example.*
import rt.animus.example.Example.*
import rt.animus.ui.theme.*

//region === Composables ===

@ExperimentalAnimationApi
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  AnimusTheme {
    ExampleLaunchScreen()
  }
}

@ExperimentalAnimationApi
@Composable
fun ExampleLaunchScreen() {
  var example by remember { mutableStateOf(NONE) }
  Scaffold(
    topBar = { AppBar(lastExample = example, onBackClick = { example = NONE }) }
  ) {
    AnimatedContentExamples(example, onExampleClick = { newExample -> example = newExample })
//    CrossfadeContent(example, onExampleClick = { newExample -> example = newExample })
  }
}

@Composable
fun CrossfadeContent(example: Example, onExampleClick: (Example) -> Unit) {
  // Crossfade transition wraps around content.
  Crossfade(targetState = example) { targetExample ->
    // Change the content based on the new, target example.
    // If we do not have a example selected,
    // then show the examples list so one can be selected.
    if (targetExample != NONE) ExampleContent(example = targetExample) else {
      ExampleList(onExampleClick = onExampleClick)
    }
  }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExamples(example: Example, onExampleClick: (Example) -> Unit) {
  AnimatedContent(
    // Animate the content based on which example is selected
    targetState = example,
    transitionSpec = {

      // If any of the examples are selected,
      // then we slide in the selected example screen
      // while fading out the examples list.
      if (targetState != NONE) {
        (slideInHorizontally(
          animationSpec = tween(500),
          initialOffsetX = { fullWidth -> fullWidth }
        ) with fadeOut(
          animationSpec = tween(350, delayMillis = 150)
        ))
          // When the target content (an example screen) slides in,
          // we have it a higher z-index so that on both slide in and slide out
          // the example screen is always above the examples list.
          .apply { targetContentZIndex = 10f }

      // If none of the examples are selected,
      // then we slide out the last selected example screen
      // while fading in the examples list.
      } else {
        fadeIn(
          animationSpec = tween(250)
        ) with slideOutHorizontally(
          animationSpec = tween(350),
          targetOffsetX = { fullWidth -> fullWidth }
        )
      }
    }
  ) { targetExample ->
    // Change the content based on the new, target example.
    if (targetExample != NONE) {
      ExampleContent(example = targetExample)
    } else {
      ExampleList(onExampleClick = onExampleClick)
    }
  }
}

@Composable
fun ExampleContent(example: Example) {
  when (example) {
    LOW_LEVEL_APIS -> LowLeveApiExample()
    COMPOSE_ANIMATED_VISIBILITY -> AnimatedVisibilityExample()
    ANDROID_PHYSICS -> PhysicsViewExample()
    COMPOSE_ANIMATE_AS_STATE -> AnimateAsStateExample()
    ANIMATED_VECTORS -> AnimatedVectorExample()
    else -> error("Invalid value for example content.")
  }
}

@Composable
fun ExampleList(onExampleClick: (Example) -> Unit) {
  LazyColumn(modifier = Modifier.fillMaxSize()) {
    item {
      Text(
        text = "Examples",
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
          .padding(start = CONTENT_PADDING_HORIZONTAL)
          .padding(vertical = CONTENT_PADDING_VERTICAL)
      )
    }
    items(Example.values().filter(Example::show)) { example ->
      ExampleItem(example) { onExampleClick(example) }
    }
  }
}

@Composable
private fun ExampleItem(example: Example, onItemClick: (Example) -> Unit) {
  Row(
    modifier = Modifier
      .defaultMinSize(minHeight = LIST_ITEM_HEIGHT_MIN)
      .fillMaxWidth()
      .clickable { onItemClick(example) }
      .padding(
        horizontal = LIST_ITEM_PADDING_HORIZONTAL,
        vertical = LIST_ITEM_PADDING_VERTICAL
      ),
    verticalAlignment = Alignment.CenterVertically
  ) {
    val iconResId = example.iconResId
    Icon(
      painter = painterResource(id = iconResId),
      tint = iconResId.tint,
      contentDescription = stringResource(id = example.titleResId),
      modifier = Modifier
        .size(LIST_ITEM_ICON_SIZE)
        .clip(CircleShape)
        .border(1.dp, Color.Gray, CircleShape)
        .padding(4.dp)
    )
    Spacer(modifier = Modifier.width(LIST_ITEM_PADDING_HORIZONTAL))
    Text(
      text = stringResource(id = example.titleResId),
      style = MaterialTheme.typography.subtitle1
    )
  }
}


@Composable
private fun AppBar(lastExample: Example, onBackClick: () -> Unit) {
  TopAppBar(
    navigationIcon = backButton(lastExample = lastExample, onBackClick),
    title = { Text(text = stringResource(id = R.string.app_name)) },
    elevation = 16.dp
  )
}

@Composable
fun backButton(lastExample: Example, onBackClick: () -> Unit): (@Composable () -> Unit)? =
  if (lastExample != NONE) {
    {
      IconButton(onClick = onBackClick) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
      }
    }
  } else null

//endregion

//region === Resource extensions/utilities ===

val Int.tint: Color
  get() = when (this) {
    R.drawable.ic_fruit_banana -> Yellow500
    R.drawable.ic_fruit_cherry -> Pink900
    R.drawable.ic_fruit_coconut -> Brown700
    R.drawable.ic_fruit_dragonfruit -> Pink400
    R.drawable.ic_fruit_grapes -> Purple800
    R.drawable.ic_fruit_lemon -> YellowA200
    R.drawable.ic_fruit_mango -> Amber600
    R.drawable.ic_fruit_mangosteen -> DeepPurple900
    R.drawable.ic_fruit_peach -> DeepOrange300
    R.drawable.ic_fruit_pineapple -> AmberA700
    else -> Color.Transparent
  }

//endregion