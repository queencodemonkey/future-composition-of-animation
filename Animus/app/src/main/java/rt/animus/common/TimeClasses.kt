package rt.animus.common

interface ScaledPeriod {
  val timeScale: Float
  val millis: Long
}

@JvmInline
value class ScaledHour(override val timeScale: Float): ScaledPeriod {
  init {
    require(timeScale > 0f)
  }

  override val millis: Long
    get() = (3600000 / timeScale).toLong()
}

@JvmInline
value class ScaledMinute(override val timeScale: Float): ScaledPeriod {
  init {
    require(timeScale > 0f)
  }

  override val millis: Long
    get() = (60000 / timeScale).toLong()
}

@JvmInline
value class ScaledSecond(override val timeScale: Float): ScaledPeriod {
  init {
    require(timeScale > 0f)
  }

  override val millis: Long
    get() = (1000 / timeScale).toLong()
}
