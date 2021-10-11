plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    compileSdk = Android.targetSdk

    defaultConfig {
        applicationId = "rt.animus"
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = versionMajor * 10000 + versionMinor * 1000 + versionPatch * 100 + versionBuild
        versionName = "$versionMajor.$versionMinor.$versionPatch"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = Versions.TargetJvm
        freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.AndroidX.Compose
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    fun implementationsFrom(dependencies: Array<String>) {
        for (dep in dependencies) {
            implementation(dep)
        }
    }

    // Android
    implementation(Dependencies.AndroidX.CoreKtx)
    implementation(Dependencies.Material)

    // Compose
    implementation(Dependencies.AndroidX.Compose.Animation.Animation)
    implementation(Dependencies.AndroidX.Compose.Animation.Core)
    implementation(Dependencies.AndroidX.Compose.Animation.Graphics)
    implementation(Dependencies.AndroidX.Compose.Material)
    implementation(Dependencies.AndroidX.Compose.Ui)
    implementation(Dependencies.AndroidX.Compose.UiTooling)
    implementation(Dependencies.AndroidX.ComposeActivity)
    implementation(Dependencies.Accompanist.SystemUiController)

    debugImplementation(Dependencies.AndroidX.Compose.UiTooling)
    androidTestImplementation(Dependencies.AndroidX.Compose.UiTest)

    // Lifecycle
    implementation(Dependencies.AndroidX.Lifecycle.Runtime)

    // Testing
    testImplementation(Dependencies.Testing.JUnit)
    androidTestImplementation(Dependencies.Testing.AndroidTest.JUnit)
    androidTestImplementation(Dependencies.Testing.AndroidTest.Espresso)
}