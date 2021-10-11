const val versionMajor = 0
const val versionMinor = 1
const val versionPatch = 0
const val versionBuild = 0

object Android {
  const val targetSdk = 31
  const val minSdk = 27
}

object Versions {
  const val Gradle = "7.0.2"
  const val Kotlin = "1.5.30"
  const val KotlinGradle = "1.5.3"
  const val TargetJvm = "11"

  object AndroidX {
    const val Compose = "1.0.3"
    const val ComposeActivity = "1.3.0-rc01"
    const val CoreKtx = "1.6.0"
    const val DataStore = "1.0.0-rc01"

    object Lifecycle {
      const val Runtime = "2.3.1"
      const val LifecycleViewModelCompose = "1.0.0-alpha07"
    }

    const val Room = "2.3.0"
  }

  object Google {
    const val Accompanist = "0.19.0"
    const val Hilt = "2.37"
    const val Material = "1.4.0"
  }

  object KotlinX {
    const val Coroutines = "1.5.0"
    const val DateTimeJvm = "0.2.1"
    const val Serialization = "1.2.2"
  }

  const val OkHttp = "4.9.0"

  object Retrofit {
    const val Retrofit2 = "2.9.0"
    const val SerializationConverter = "0.8.0"
  }

  const val Stetho = "1.5.1"

  object Testing {
    const val JUnit = "4.13.2"

    object AndroidX {
      const val JUnit = "1.1.3"
      const val EspressoCore = "3.4.0"
    }
  }

  const val Timber = "4.7.1"
}

object Dependencies {
  const val Material = "com.google.android.material:material:${Versions.Google.Material}"

  object AndroidX {
    const val CoreKtx = "androidx.core:core-ktx:${Versions.AndroidX.CoreKtx}"
    object Compose {
      object Animation {
        const val Animation = "androidx.compose.animation:animation:${Versions.AndroidX.Compose}"
        const val Core = "androidx.compose.animation:animation-core:${Versions.AndroidX.Compose}"
        const val Graphics = "androidx.compose.animation:animation-graphics:1.1.0-alpha05"
      }
      const val Material = "androidx.compose.material:material:${Versions.AndroidX.Compose}"
      const val Ui = "androidx.compose.ui:ui:${Versions.AndroidX.Compose}"
      const val UiTooling =  "androidx.compose.ui:ui-tooling:${Versions.AndroidX.Compose}"
      const val UiTest = "androidx.compose.ui:ui-test-junit4:${Versions.AndroidX.Compose}"
    }
    const val ComposeActivity = "androidx.activity:activity-compose:${Versions.AndroidX.ComposeActivity}"
    const val DataStore = "androidx.datastore:datastore-preferences:${Versions.AndroidX.DataStore}"

    object Lifecycle {
      const val Runtime =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.AndroidX.Lifecycle.Runtime}"
      const val ViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.AndroidX.Lifecycle.LifecycleViewModelCompose}"
      const val ViewModelSavedState =
        "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.AndroidX.Lifecycle.LifecycleViewModelCompose}"
    }

    object Room {
      const val Ktx = "androidx.room:room-ktx:${Versions.AndroidX.Room}"
      const val Compiler = "androidx.room:room-compiler:${Versions.AndroidX.Room}"
      const val Runtime = "androidx.room:room-runtime:${Versions.AndroidX.Room}"
    }
  }

  object Accompanist {
    private const val GroupIdPrefix = "com.google.accompanist"
    const val SystemUiController = "$GroupIdPrefix:accompanist-systemuicontroller:${Versions.Google.Accompanist}"
  }

  object Hilt {
    private const val GroupIdPrefix = "com.google.dagger"
    const val HiltAndroid = "$GroupIdPrefix:hilt-android:${Versions.Google.Hilt}"
    const val HiltCompiler = "$GroupIdPrefix:hilt-compiler:${Versions.Google.Hilt}"
  }

  object KotlinX {
    const val GroupIdPrefix = "org.jetbrains.kotlinx"
    const val Coroutines = "$GroupIdPrefix:kotlinx-coroutines-core:${Versions.KotlinX.Coroutines}"
    const val DateTimeJvm = "$GroupIdPrefix:kotlinx-datetime-jvm:${Versions.KotlinX.DateTimeJvm}"

    object Serialization {
      const val Json = "$GroupIdPrefix:kotlinx-serialization-json:${Versions.KotlinX.Serialization}"
    }
  }

  object OkHttp {
    private const val GroupId = "com.squareup.okhttp3"
    const val OkHttp = "$GroupId:okhttp:${Versions.OkHttp}"
  }

  object Retrofit {
    const val Retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.Retrofit.Retrofit2}"
    const val SerializationConverter =
      "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:${Versions.Retrofit.SerializationConverter}"
  }

  const val Timber = "com.jakewharton.timber:timber:${Versions.Timber}"

  object Testing {
    const val JUnit = "junit:junit:${Versions.Testing.JUnit}"
    object AndroidTest {
      const val JUnit = "androidx.test.ext:junit:${Versions.Testing.AndroidX.JUnit}"
      const val Espresso = "androidx.test.espresso:espresso-core:${Versions.Testing.AndroidX.EspressoCore}"
    }
  }
}