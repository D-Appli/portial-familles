plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "fr.dappli.portailfamilles.core.data.repository"
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
    implementation(libs.ktor.client.okhttp)
    implementation(libs.hilt.core)

    implementation(projects.core.domain.irepository)
    implementation(projects.core.data.idatasource)
    implementation(projects.core.kotlin)

    ksp(libs.hilt.compiler)
}
