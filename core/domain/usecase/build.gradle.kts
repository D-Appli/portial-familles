plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "fr.dappli.portailfamilles.core.domain.usecase"
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
    implementation(libs.hilt.core)

    api(projects.core.domain.irepository)
    api(projects.core.domain.model)

    ksp(libs.hilt.compiler)
}
