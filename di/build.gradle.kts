plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktlint)
    alias(libs.plugins.metro)
}

android {
    namespace = "com.nicholas.rutherford.wag.trail.di"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        minSdk = 24
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    api(project(":base:vm"))
    api(project(":feature:dogs"))

    implementation(libs.androidx.lifecycle.viewmodel)
}
