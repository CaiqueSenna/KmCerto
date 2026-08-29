plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.caiquesenna.kmcerto"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.caiquesenna.kmcerto"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    // -- AndroidX Core --
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.constraintlayout)

    // -- Room Database --
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.room.compiler)

    // -- Lifecycle (ViewModel + LiveData --
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)

    // -- Coroutines --
    implementation(libs.kotlinx.coroutines.android)

    // -- Retrofit (HTTP / API) --
    implementation(libs.retrofit)
    implementation(libs.retrofit.gson)
    implementation(libs.okhttp.logging)

    // -- Glide (imagens) --
    implementation(libs.glide)
    ksp(libs.glide.ksp)

    // -- WorkManager --
    implementation(libs.work.runtime.ktx)

    // -- MPAndroidChart (Gráficos) --
    implementation(libs.mpandroidchart)

    // -- Testes --
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.junit)
}