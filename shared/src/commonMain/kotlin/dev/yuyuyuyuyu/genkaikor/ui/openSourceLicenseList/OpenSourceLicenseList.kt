package dev.yuyuyuyuyu.genkaikor.ui.openSourceLicenseList

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.mikepenz.aboutlibraries.ui.compose.m3.LibrariesContainer
import com.mikepenz.aboutlibraries.ui.compose.produceLibraries
import com.mikepenz.aboutlibraries.ui.compose.variant.LibraryBadges
import genkaikor.shared.generated.resources.Res

@Composable
fun OpenSourceLicenseList(modifier: Modifier = Modifier) {
    val libraries by produceLibraries {
        Res.readBytes("files/aboutlibraries.json").decodeToString()
    }

    LibrariesContainer(
        libraries = libraries?.let { libs ->
            libs.copy(libraries = libs.libraries.distinctBy { it.name })
        },
        modifier = modifier,
        badges = LibraryBadges(description = true),
    )
}
