import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.composePwa)

    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

kotlin {
    js {
        browser()
        binaries.executable()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)

            implementation(libs.compose.ui)
        }
    }
}

detekt {
    // The Kotlin Multiplatform source set layout is not among Detekt's default source directories,
    // and pointing at `src` keeps the generated sources under `build` out of the analysis.
    source.setFrom("src")
    buildUponDefaultConfig = true
    config.setFrom(rootProject.file("detekt.yml"))
}

ktlint {
    filter {
        // Compose Multiplatform adds its generated resource accessors to the Kotlin source sets.
        exclude("**/generated/**")
    }
}
