package dev.yuyuyuyuyu.genkaikor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform