package dev.yuyuyuyuyu.genkaikor.domain.useCase

class InsertDakutenUseCase {
    companion object {
        val IGNORE_LIST = listOf('ー', '〜', '!', '?', '！', '？', '\n')
    }

    operator fun invoke(text: String): String = text.map {
        if (IGNORE_LIST.contains(it)) it else "$it゛"
    }.joinToString("")
}
