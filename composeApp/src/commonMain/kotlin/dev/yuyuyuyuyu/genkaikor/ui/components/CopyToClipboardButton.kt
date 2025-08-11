package dev.yuyuyuyuyu.genkaikor.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@Composable
fun CopyToClipboardButton(
    textToCopy: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    snackbarHostState: SnackbarHostState? = null,
) {
    val scope = rememberCoroutineScope()
    val clipboardManager = LocalClipboardManager.current

    Button(
        modifier = modifier,
        enabled = enabled,
        onClick = {
            clipboardManager.setText(AnnotatedString(textToCopy))
            snackbarHostState?.let {
                scope.launch {
                    it.showSnackbar("クリップボードへコピーしました")
                }
            }
        },
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(Icons.Default.ContentCopy, contentDescription = "Copy to clipboard")
            Text("Copy to clipboard")
        }
    }
}
