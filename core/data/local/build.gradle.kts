plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)
}

android {
    namespace = "fr.dappli.portailfamilles.core.data.local"
    // TODO move to common gradle file
    compileSdk = 34
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.hilt.core)

    api(projects.core.data.api)
    implementation(libs.androidx.datastore.core)
    implementation(libs.androidx.datastore.preferences)
    implementation(libs.hilt.android)

    ksp(libs.hilt.compiler)
}
