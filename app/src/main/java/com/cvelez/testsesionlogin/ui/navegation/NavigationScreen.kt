package com.cvelez.testsesionlogin.ui.navegation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.cvelez.testsesionlogin.ui.view.Home
import com.cvelez.testsesionlogin.ui.view.Login
import com.cvelez.testsesionlogin.ui.viewmodel.LoginViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.compose.runtime.*

@Composable
fun NavigationScreen(navController: NavHostController, startDestination: String) {
    NavHost(navController = navController, startDestination = startDestination) {
        composable(Destination.Login.route) {
            LoginScreen(navController = navController)
        }
        composable(Destination.Home.route) {
            val viewModel: LoginViewModel = hiltViewModel()
            Home(viewModel = viewModel, navController = navController)
        }
    }
}

@Composable
fun LoginScreen(
    navController: NavController
) {
    val viewModel: LoginViewModel = hiltViewModel()

    val isSuccessLoading by viewModel.isSuccessLoading.collectAsState()
    val imageErrorAuth by viewModel.imageErrorAuth.collectAsState()
    val progressBar by viewModel.progressBar.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    if (isSuccessLoading) {
        LaunchedEffect(Unit) {
            navController.navigate(Destination.Home.route) {
                popUpTo(Destination.Login.route) {
                    inclusive = true
                }
            }
        }
    }

    Login(
        loadingProgressBar = progressBar,
        onclickLogin = { email, password -> viewModel.login(email, password) },
        imageError = imageErrorAuth,
        errorMessage = errorMessage
    )
}


