plugins {
    // this is necessary to avoid the plugins to be loaded multiple times
    // in each subproject's classloader
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinJvm) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false

    // The root project holds no Kotlin source, but ktlint also covers the Gradle script files.
    alias(libs.plugins.ktlint)

    alias(libs.plugins.versionCatalogUpdate)
}
