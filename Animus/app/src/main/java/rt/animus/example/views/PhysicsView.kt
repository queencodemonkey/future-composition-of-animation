package rt.animus.example.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.dynamicanimation.animation.DynamicAnimation
import androidx.dynamicanimation.animation.SpringAnimation
import androidx.dynamicanimation.animation.SpringForce
import rt.animus.R

class PhysicsView(context: Context, attrs: AttributeSet?) : FrameLayout(context, attrs) {

  override fun onFinishInflate() {
    super.onFinishInflate()

    val vector = findViewById<ImageView>(R.id.vector)
    findViewById<Button>(R.id.button_spring).setOnClickListener {
      val offsetX = (resources.displayMetrics.widthPixels - vector.width) / 2
      val offsetY = (resources.displayMetrics.heightPixels - vector.height) / 2

      val finalPositionX = (-offsetX..offsetX).random().toFloat()
      val finalPositionY = (-offsetY..offsetY).random().toFloat()
      vector.spring(SpringAnimation.TRANSLATION_X).animateToFinalPosition(finalPositionX)
      vector.spring(SpringAnimation.TRANSLATION_Y).animateToFinalPosition(finalPositionY)
    }
  }

  /**
   * Extension method for easily managing spring animations on a view.
   * From Nick Butcher's "Motional intelligence: Build smarter animations (Google I/O'19)":
   * https://youtu.be/f3Lm8iOr4mE
   */
  private fun View.spring(property: DynamicAnimation.ViewProperty): SpringAnimation {
    val key = getKey(property)
    var springAnimation = getTag(key) as? SpringAnimation
    if (springAnimation == null) {
      springAnimation = SpringAnimation(this, property).apply {
        spring = SpringForce().apply {
          dampingRatio = SpringForce.DAMPING_RATIO_MEDIUM_BOUNCY
          stiffness = SpringForce.STIFFNESS_VERY_LOW
          setTag(key, springAnimation)
        }
      }
    }
    return springAnimation
  }

  private fun getKey(property: DynamicAnimation.ViewProperty) = when (property) {
    TRANSLATION_X -> "translationX".hashCode()
    TRANSLATION_Y -> "translationY".hashCode()
    else -> "spring".hashCode()
  }
}