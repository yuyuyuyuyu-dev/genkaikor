package dev.yuyuyuyuyu.genkaikor.domain.useCase

import kotlin.test.Test
import kotlin.test.assertEquals

class InsertDakutenUseCaseTest {

    private val insertDakutenUseCase = InsertDakutenUseCase()

    @Test
    fun `invoke should add dakuten to each character not in IGNORE_LIST`() {
        // Arrange
        val inputText = "テストー〜！？!?\n"
        val expected = "テ゛ス゛ト゛ー〜！？!?\n"

        // Act
        val actual = insertDakutenUseCase(inputText)

        // Assert
        assertEquals(expected, actual)
    }
}
