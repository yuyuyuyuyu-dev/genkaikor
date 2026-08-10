import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.aboutLibraries)

    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

kotlin {
    jvm()

    js {
        browser()
    }

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.compose.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(libs.circuit)
            implementation(project.dependencies.platform(libs.koin.bom))
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewModel)
            implementation(libs.koin.compose.viewModel.navigation)
            implementation(libs.compose.materialIconsExtended)
            implementation(libs.aboutlibraries.compose.m3)

            implementation(libs.myMaterialTheme)
            implementation(libs.simpleTopAppBar)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
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

aboutLibraries {
    export {
        outputFile = file("src/commonMain/composeResources/files/aboutlibraries.json")
    }
}
