package rt.animus.example.views

import android.content.Context
import android.graphics.drawable.AnimatedVectorDrawable
import android.util.AttributeSet
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import rt.animus.R

class VectorDrawableView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

  override fun onFinishInflate() {
    super.onFinishInflate()

    val vectorViews = listOf<ImageView>(
      findViewById(R.id.anim_board_to_card),
      findViewById(R.id.anim_clock),
      findViewById(R.id.anim_checked),
    )
    vectorViews.forEach { imageView ->
      val drawable = imageView.drawable
      if (drawable is AnimatedVectorDrawable) {
        drawable.reset()
      }
      imageView.setOnClickListener {
        if (drawable is AnimatedVectorDrawable) {
          if (drawable.isRunning) {
            drawable.stop()
          } else {
            drawable.start()
          }
        } else if (drawable is AnimatedVectorDrawableCompat) {
          drawable.start()
        }
      }
    }

    findViewById<Button>(R.id.button_reset).setOnClickListener {
      vectorViews.forEach { imageView ->
        val drawable = imageView.drawable
        if (drawable is AnimatedVectorDrawable) {
          drawable.reset()
        }
      }
    }


  }

}