package com.cvelez.testsesionlogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.cvelez.testsesionlogin.ui.navegation.Destination
import com.cvelez.testsesionlogin.ui.navegation.NavigationScreen
import com.cvelez.testsesionlogin.ui.theme.TestSesionLoginTheme
import com.cvelez.testsesionlogin.ui.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestSesionLoginTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavigationScreen(navController = navController, startDestination = Destination.Login.route)
                }
            }
        }
    }
}


