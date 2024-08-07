package com.cvelez.testsesionlogin.ui.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import com.cvelez.testsesionlogin.ui.components.ButtonLogin
import com.cvelez.testsesionlogin.ui.components.EmailOutTextField
import com.cvelez.testsesionlogin.ui.components.ErrorImageAuth
import com.cvelez.testsesionlogin.ui.components.ImageLogin
import com.cvelez.testsesionlogin.ui.components.PasswordOutTextField
import com.cvelez.testsesionlogin.ui.components.ProgressBarLoading
import com.cvelez.testsesionlogin.ui.components.TextLogin

@SuppressLint("UnrememberedMutableState")
@Composable
fun Login(
    modifier: Modifier = Modifier,
    loadingProgressBar: Boolean,
    onclickLogin: (email: String, password: String) -> Unit,
    imageError: Boolean,
    errorMessage: String?
) {
    var email by rememberSaveable { mutableStateOf(value = "") }
    var password by rememberSaveable { mutableStateOf(value = "") }
    val isValidate by derivedStateOf { email.isNotBlank() && password.isNotBlank() }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ImageLogin()

        TextLogin()

        Spacer(modifier = modifier.height(15.dp))

        EmailOutTextField(
            textValue = email,
            onValueChange = { email = it },
            onClickButton = { email = "" },
            onNext = { focusManager.moveFocus(FocusDirection.Down) }
        )

        Spacer(modifier = modifier.height(15.dp))

        PasswordOutTextField(
            textValue = password,
            onValueChange = { password = it },
            onDone = {
                focusManager.clearFocus()
            }
        )

        Spacer(modifier = modifier.height(35.dp))

        ButtonLogin(
            onclick = { onclickLogin(email, password) },
            enabled = isValidate
        )

        Spacer(modifier = modifier.height(20.dp))

        errorMessage?.let {
            Text(text = it, color = MaterialTheme.colors.error)
        }
    }

    ErrorImageAuth(isImageValidate = imageError)

    ProgressBarLoading(isLoading = loadingProgressBar)
}