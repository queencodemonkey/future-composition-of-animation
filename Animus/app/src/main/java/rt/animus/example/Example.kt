package rt.animus.example

import androidx.core.content.res.ResourcesCompat
import rt.animus.R

//region === Definitions ===

enum class Example(val show: Boolean = false) {
  LOW_LEVEL_APIS(show = true),
  COMPOSE_ANIMATED_VISIBILITY(show = true),
  ANDROID_PHYSICS(show = true),
  COMPOSE_ANIMATE_AS_STATE(show = true),
  ANIMATED_VECTORS(show = true),
  ANDROID_TRANSITIONS,
  COMPOSE_ANIMATE_CONTENT_SIZE,
  COMPOSE_UPDATE_TRANSITION,
  COMPOSE_ANIMATION_STATE,
  COMPOSE_ANIMATION_VECTOR,
  NONE
}

//endregion

//region === Extensions ===

val Example.titleResId: Int
  get() = when (this) {
    Example.LOW_LEVEL_APIS -> R.string.api_low_level
    Example.ANIMATED_VECTORS -> R.string.api_animated_vectors
    Example.ANDROID_PHYSICS -> R.string.api_android_physics
    Example.ANDROID_TRANSITIONS -> R.string.api_android_transitions
    Example.COMPOSE_ANIMATED_VISIBILITY -> R.string.api_compose_animated_visibility
    Example.COMPOSE_ANIMATE_CONTENT_SIZE -> R.string.api_compose_content_size
    Example.COMPOSE_UPDATE_TRANSITION -> R.string.api_compose_update_transition
    Example.COMPOSE_ANIMATE_AS_STATE -> R.string.api_compose_animate_as_state
    Example.COMPOSE_ANIMATION_STATE -> R.string.api_compose_animate
    Example.COMPOSE_ANIMATION_VECTOR -> R.string.api_compose_animation_vector
    Example.NONE -> ResourcesCompat.ID_NULL
  }

val Example.iconResId: Int
  get() = when (this) {
    Example.LOW_LEVEL_APIS -> R.drawable.ic_fruit_mango
    Example.ANIMATED_VECTORS -> R.drawable.ic_fruit_cherry
    Example.ANDROID_PHYSICS -> R.drawable.ic_fruit_coconut
    Example.ANDROID_TRANSITIONS -> R.drawable.ic_fruit_dragonfruit
    Example.COMPOSE_ANIMATED_VISIBILITY -> R.drawable.ic_fruit_grapes
    Example.COMPOSE_ANIMATE_CONTENT_SIZE -> R.drawable.ic_fruit_lemon
    Example.COMPOSE_UPDATE_TRANSITION -> R.drawable.ic_fruit_banana
    Example.COMPOSE_ANIMATE_AS_STATE -> R.drawable.ic_fruit_peach
    Example.COMPOSE_ANIMATION_STATE -> R.drawable.ic_fruit_mangosteen
    Example.COMPOSE_ANIMATION_VECTOR -> R.drawable.ic_fruit_pineapple
    Example.NONE -> ResourcesCompat.ID_NULL
  }

//endregion