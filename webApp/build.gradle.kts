import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.aboutLibraries)
    alias(libs.plugins.composePwa)

    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

kotlin {
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser()
        binaries.executable()
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.shared)

            implementation(libs.compose.ui)
            implementation(libs.compose.components.resources)
            implementation(libs.aboutlibraries.compose.core)
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
    version = libs.versions.ktlintCli.get()
    filter {
        val buildPath =
            layout.buildDirectory
                .get()
                .asFile.path + File.separator
        exclude { it.file.path.startsWith(buildPath) }
    }
}

val aboutLibrariesResources = layout.buildDirectory.dir("aboutLibraries/composeResources")

aboutLibraries {
    export {
        outputFile = aboutLibrariesResources.map { it.file("files/aboutlibraries.json") }
    }
}

compose.resources {
    customDirectory(
        sourceSetName = "commonMain",
        directoryProvider = tasks.named("exportLibraryDefinitions").map { aboutLibrariesResources.get() },
    )
}
