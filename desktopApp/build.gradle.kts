import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)

    alias(libs.plugins.aboutLibraries)

    alias(libs.plugins.detekt)
    alias(libs.plugins.ktlint)
}

detekt {
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

dependencies {
    implementation(projects.shared)

    implementation(compose.desktop.currentOs)
    implementation(libs.kotlinx.coroutinesSwing)
    implementation(libs.compose.components.resources)
    implementation(libs.aboutlibraries.compose.core)

    implementation(libs.compose.uiToolingPreview)
}

val aboutLibrariesResources = layout.buildDirectory.dir("aboutLibraries/composeResources")

aboutLibraries {
    export {
        outputFile = aboutLibrariesResources.map { it.file("files/aboutlibraries.json") }
    }
}

compose.resources {
    customDirectory(
        sourceSetName = "main",
        directoryProvider = tasks.named("exportLibraryDefinitions").map { aboutLibrariesResources.get() },
    )
}

compose.desktop {
    application {
        mainClass = "dev.yuyuyuyuyu.genkaikor.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "dev.yuyuyuyuyu.genkaikor"
            packageVersion = "1.0.0"
        }
    }
}
