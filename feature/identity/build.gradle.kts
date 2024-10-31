plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "fr.dappli.portailfamilles.feature.indentity"
    // TODO move to common gradle file
    compileSdk = 34

    defaultConfig {
        minSdk = 24
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.material3)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.ktor.client.okhttp)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose)

    // project libraries
    implementation(projects.core.data.remote)
}
