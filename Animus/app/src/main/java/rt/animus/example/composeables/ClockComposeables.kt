package rt.animus.example.composeables

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.async
import rt.animus.R
import rt.animus.ui.theme.*
import kotlin.math.roundToInt

@Composable
fun Clock() {

  //region == Drawing Properties ==

  // Subtract half a stroke length since strokes are always aligned center.
  val radius = (CLOCK_SIZE - CLOCK_STROKE) / 2
  val faceBrush = Brush.linearGradient(colors = listOf(Pink400, Pink900))
  val minuteBrush = SolidColor(Color.DarkGray)
  val secondBrush = SolidColor(Color.Red)

  // endregion

  //region == Animtables ==

  val minuteRotationAnimatable = remember { Animatable(0f) }
  val secondRotationAnimatable = remember { Animatable(0f) }

  //endregion

  Canvas(
    modifier = Modifier
      .padding(dimensionResource(id = R.dimen.content_padding_vertical))
      .size(CLOCK_SIZE)
  ) {
    val radiusPx = radius.toPx()
    val clockStrokePx = CLOCK_STROKE.toPx()
    val radiusVerticalOffset = Offset(0f, radiusPx)

    //region == Clock Ticks ==

    // Local function for drawing an hour marker tick.
    fun drawTick(tickLength: Dp, tickStroke: Dp) {
      drawLine(
        brush = faceBrush,
        start = center + radiusVerticalOffset,
        end = center + Offset(0f, radiusPx - tickLength.toPx()),
        strokeWidth = tickStroke.toPx()
      )
    }

    // Draw all of the hour marker ticks on the clock face.
    (0..360 step 30).forEach { rotation ->
      val degrees = rotation.toFloat()
      // When drawing the 12, 3, 6, or 9 ticks, use the large tick.
      if (rotation % 90 == 0) {
        rotate(degrees) { drawTick(CLOCK_TICK_SIZE_LARGE, CLOCK_TICK_WIDTH_LARGE) }
        // When drawing any other tick, use the small tick.
      } else {
        rotate(degrees) { drawTick(CLOCK_TICK_SIZE_SMALL, CLOCK_TICK_WIDTH_SMALL) }
      }
    }

    //endregion

    //region == Clock Outline ==

    // Draw the clock outline.
    drawCircle(
      brush = faceBrush,
      radius = radius.toPx(),
      style = Stroke(width = clockStrokePx)
    )

    //endregion

    //region == Clock Hands ==

    // Local function for drawing clock hands.
    fun drawHand(brush: Brush, rotation: Float, width: Float) {
      // Adding 180 degrees because our "origin" is really 12 o'clock
      rotate(rotation + 180f) {
        drawLine(
          brush = brush,
          start = center,
          end = center + radiusVerticalOffset - Offset(0f, clockStrokePx * 1.5f),
          strokeWidth = width,
          cap = StrokeCap.Round
        )
      }
    }

    drawHand(minuteBrush, minuteRotationAnimatable.value, CLOCK_HAND_WIDTH_MINUTE.toPx())
    drawHand(secondBrush, secondRotationAnimatable.value, CLOCK_HAND_WIDTH_SECOND.toPx())

    //endregion

  }

  LaunchedEffect(Unit) {
    minuteRotationAnimatable.animateTo(
      targetValue = 360f,
      animationSpec = TweenSpec(
        durationMillis = DEFAULT_DURATION_MILLIS,
        easing = LinearEasing
      )
    )
  }

  LaunchedEffect(Unit) {
    secondRotationAnimatable.animateTo(
      targetValue = 3600f,
      animationSpec = TweenSpec(
        durationMillis = DEFAULT_DURATION_MILLIS,
        easing = LinearEasing
      )
    )
  }
}

@Composable
fun TimescaledClock(timescale: Float, onDone: () -> Unit) {

  //region == Drawing Properties ==

  // Subtract half a stroke length since strokes are always aligned center.
  val radius = (CLOCK_SIZE - CLOCK_STROKE) / 2
  val faceBrush = Brush.linearGradient(colors = listOf(Pink400, Pink900))
  val minuteBrush = SolidColor(Color.DarkGray)
  val secondBrush = SolidColor(Color.Red)

  // endregion

  //region == Animtables ==

  val minuteRotationAnimatable = remember { Animatable(0f) }
  val secondRotationAnimatable = remember { Animatable(0f) }

  //endregion

  Canvas(
    modifier = Modifier
      .padding(dimensionResource(id = R.dimen.content_padding_vertical))
      .size(CLOCK_SIZE)
  ) {
    val radiusPx = radius.toPx()
    val clockStrokePx = CLOCK_STROKE.toPx()
    val radiusVerticalOffset = Offset(0f, radiusPx)

    //region == Clock Ticks ==

    // Local function for drawing an hour marker tick.
    fun drawTick(tickLength: Dp, tickStroke: Dp) {
      drawLine(
        brush = faceBrush,
        start = center + radiusVerticalOffset,
        end = center + Offset(0f, radiusPx - tickLength.toPx()),
        strokeWidth = tickStroke.toPx()
      )
    }

    // Draw all of the hour marker ticks on the clock face.
    (0..360 step 30).forEach { rotation ->
      val degrees = rotation.toFloat()
      // When drawing the 12, 3, 6, or 9 ticks, use the large tick.
      if (rotation % 90 == 0) {
        rotate(degrees) { drawTick(CLOCK_TICK_SIZE_LARGE, CLOCK_TICK_WIDTH_LARGE) }
        // When drawing any other tick, use the small tick.
      } else {
        rotate(degrees) { drawTick(CLOCK_TICK_SIZE_SMALL, CLOCK_TICK_WIDTH_SMALL) }
      }
    }

    //endregion

    //region == Clock Outline ==

    // Draw the clock outline.
    drawCircle(
      brush = faceBrush,
      radius = radius.toPx(),
      style = Stroke(width = clockStrokePx)
    )

    //endregion

    //region == Clock Hands ==

    // Local function for drawing clock hands.
    fun drawHand(brush: Brush, rotation: Float, width: Float) {
      // Adding 180 degrees because our "origin" is really 12 o'clock
      rotate(rotation + 180f) {
        drawLine(
          brush = brush,
          start = center,
          end = center + radiusVerticalOffset - Offset(0f, clockStrokePx * 1.5f),
          strokeWidth = width,
          cap = StrokeCap.Round
        )
      }
    }

    drawHand(minuteBrush, minuteRotationAnimatable.value, CLOCK_HAND_WIDTH_MINUTE.toPx())
    drawHand(secondBrush, secondRotationAnimatable.value, CLOCK_HAND_WIDTH_SECOND.toPx())

    //endregion

  }

  fun calculateRemainingDuration(currentValue: Float, targetValue: Float) =
    (1f - (currentValue / targetValue)) * DEFAULT_DURATION_MILLIS

  LaunchedEffect(timescale) {
    val deferredMinute = async {
      val remainingDuration = calculateRemainingDuration(minuteRotationAnimatable.value, MINUTE_ROTATION_TARGET)
      if (remainingDuration > 0f) {
        minuteRotationAnimatable.animateTo(
          targetValue = MINUTE_ROTATION_TARGET,
          animationSpec = TweenSpec(
            durationMillis = (remainingDuration / timescale).roundToInt(),
            easing = LinearEasing
          )
        )
      }
    }
    val deferredSecond = async {
      val remainingDuration = calculateRemainingDuration(secondRotationAnimatable.value, SECOND_ROTATION_TARGET)
      if (remainingDuration > 0f) {
        secondRotationAnimatable.animateTo(
          targetValue = SECOND_ROTATION_TARGET,
          animationSpec = TweenSpec(
            durationMillis = (remainingDuration / timescale).roundToInt(),
            easing = LinearEasing
          )
        )
      }
    }
    deferredMinute.await()
    deferredSecond.await()

    onDone()
  }

//  LaunchedEffect(timescale) {
//    val remainingDuration = calculateRemainingDuration(secondRotationAnimatable.value, SECOND_ROTATION_TARGET)
//    if (remainingDuration > 0f) {
//      secondRotationAnimatable.animateTo(
//        targetValue = SECOND_ROTATION_TARGET,
//        animationSpec = TweenSpec(
//          durationMillis = (remainingDuration / timescale).roundToInt(),
//          easing = LinearEasing
//        )
//      )
//    }
//  }
}


private const val DEFAULT_DURATION_MILLIS = 30_000
private const val MINUTE_ROTATION_TARGET = 360f
private const val SECOND_ROTATION_TARGET = MINUTE_ROTATION_TARGET * 10f
