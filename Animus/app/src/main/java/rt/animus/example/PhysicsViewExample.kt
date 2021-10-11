package rt.animus.example

import android.annotation.SuppressLint
import android.view.LayoutInflater
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import rt.animus.R
import rt.animus.ui.theme.AnimusTheme


@Preview(showBackground = true)
@Composable
fun PhysicsViewPreview() {
  PhysicsViewExample()
}

@SuppressLint("InflateParams")
@Composable
fun PhysicsViewExample() {
  AnimusTheme {
    Surface(color = MaterialTheme.colors.background) {
      Box(modifier = Modifier.fillMaxSize()) {
        AndroidView(
          factory = { context ->
            LayoutInflater.from(context).inflate(R.layout.view_physics, null)
          },
          modifier = Modifier.fillMaxSize()
        )
      }
    }
  }
}
