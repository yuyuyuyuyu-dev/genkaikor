package dev.yuyuyuyuyu.genkaikor.ui.genkaikor

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.yuyuyuyuyu.genkaikor.domain.useCase.InsertDakutenUseCase

class GenkaikorViewModel(
    private val insertDakutenUseCase: InsertDakutenUseCase,
) : ViewModel() {
    var inputText by mutableStateOf("")
        private set

    val genkaiText by derivedStateOf { insertDakutenUseCase(inputText) }

    fun changeInputText(newValue: String) {
        inputText = newValue
    }
}
