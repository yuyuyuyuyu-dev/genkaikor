package dev.yuyuyuyuyu.genkaikor.ui.openSourceLicenseList

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.Libs
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.variant.LibraryBadges

@Composable
fun OpenSourceLicenseList(
    libraries: Libs?,
    modifier: Modifier = Modifier,
) {
    LibrariesContainer(
        libraries =
            libraries?.let { libs ->
                libs.copy(libraries = libs.libraries.distinctBy { it.name })
            },
        modifier = modifier,
        badges = LibraryBadges(description = true),
    )
}
