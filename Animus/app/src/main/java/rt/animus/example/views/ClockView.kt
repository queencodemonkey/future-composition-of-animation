package rt.animus.example.views

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import rt.animus.R
import rt.animus.ui.theme.Pink700

class ClockView(context: Context, attrs: AttributeSet? = null) : View(context, attrs) {

  //region === Drawing Properties ===

  private val clockStroke = resources.getDimension(R.dimen.clock_stroke_width)
  private val radius = (resources.getDimensionPixelSize(R.dimen.clock_size) - clockStroke) * 0.5f
  private val handGap = clockStroke * 1.5f
  private val tickOffsetStart = clockStroke * 0.5f
  private val tickOffsetEnd = tickOffsetStart + resources.getDimension(R.dimen.clock_tick_length)

  //endregion

  //region === Paints ===

  private val facePaint = clockPaint(
    color = Color.DarkGray,
    strokeWidth = clockStroke
  )
  private val minuteHandPaint = clockPaint(
    color = Color.Black,
    strokeWidth = clockStroke
  )
  private val secondHandPaint = clockPaint(
    color = Pink700,
    strokeWidth = resources.getDimension(R.dimen.clock_hand_width)
  )
  //endregion

  //region === Animators ===

  private val minuteHandAnimator = ValueAnimator.ofFloat(0f, 360f)
    .apply {
      duration = DURATION_DEFAULT
      interpolator = INTERPOLATOR_DEFAULT
      addUpdateListener { invalidate() }
    }
    .also { it.start() }

  private val secondHandAnimator = ValueAnimator.ofFloat(0f, 3600f)
    .apply {
      duration = DURATION_DEFAULT
      interpolator = INTERPOLATOR_DEFAULT
      addUpdateListener { invalidate() }
    }
    .also { it.start() }

  //endregion

  //region === View Implementation ===

  override fun onDraw(canvas: Canvas) {
    val centerX = width * 0.5f
    val centerY = height * 0.5f

    // Draw clock outline.
    canvas.drawCircle(centerX, centerY, radius, facePaint)
    // Draw hour marker ticks.
    (0..360 step 30).forEach { degree ->
      canvas.save()
      canvas.rotate(degree.toFloat(), centerX, centerY)
      canvas.drawLine(
        centerX,
        centerY - radius + tickOffsetStart,
        centerX,
        centerY - radius + tickOffsetEnd,
        facePaint
      )
      canvas.restore()
    }

    // Draw minute hand.
    canvas.save()
    val minuteHandDegrees = minuteHandAnimator.animatedValue as Float
    canvas.rotate(minuteHandDegrees, centerX, centerY)
    canvas.drawLine(centerX, centerY, centerX, centerY - radius + handGap, minuteHandPaint)
    canvas.restore()

    // Draw second hand.
    canvas.save()
    val secondHandleDegrees = secondHandAnimator.animatedValue as Float
    canvas.rotate(secondHandleDegrees, centerX, centerY)
    canvas.drawLine(centerX, centerY, centerX, centerY - radius + handGap, secondHandPaint)
    canvas.restore()
  }
  //endregion

  companion object {
    private const val DURATION_DEFAULT = 30_000L
    private val INTERPOLATOR_DEFAULT = LinearInterpolator()

    private fun clockPaint(color: Color, strokeWidth: Float): Paint {
      val paint = Paint()
      paint.style = Paint.Style.STROKE
      paint.color = color.toArgb()
      paint.strokeWidth = strokeWidth
      paint.strokeCap = Paint.Cap.ROUND
      return paint
    }
  }
}