package dev.yuyuyuyuyu.genkaikor.ui

sealed interface Route {
    data object Genkaikor : Route

    data object OpenSourceLicenseList : Route
}
