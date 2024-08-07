package com.cvelez.testsesionlogin.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.cvelez.testsesionlogin.R
import com.cvelez.testsesionlogin.ui.viewmodel.LoginViewModel
import com.cvelez.testsesionlogin.ui.navegation.Destination

@Composable
fun Home(viewModel: LoginViewModel = hiltViewModel(), navController: NavController) {
    val listColorBackground = listOf(
        Color(238, 113, 0, 255),
        Color(101, 0, 126, 255),
        Color(0, 47, 187),
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listColorBackground
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Icon Home",
                modifier = Modifier.size(400.dp)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Button(onClick = {
                viewModel.clearAuth()
                navController.navigate(Destination.Login.route) {
                    popUpTo(Destination.Home.route) {
                        inclusive = true
                    }
                }
            }) {
                Text(text = "Logout")
            }
        }
    }
}
