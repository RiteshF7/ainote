plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.trex.ainote"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.trex.ainote"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.runtime.android)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation("org.tensorflow:tensorflow-lite:2.10.0")

    // Jetpack Compose dependencies
    implementation("androidx.compose.ui:ui:1.4.0")
    implementation("androidx.compose.material:material:1.4.0")
    implementation("androidx.compose.ui:ui-tooling:1.4.0")
    implementation("androidx.compose.foundation:foundation:1.4.0")
    implementation("androidx.activity:activity-compose:1.7.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.1")

    // Optional: Animation and Navigation support for Compose
    implementation("androidx.compose.animation:animation:1.4.0")
    implementation("androidx.navigation:navigation-compose:2.5.3")
}