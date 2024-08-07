package com.cvelez.testsesionlogin.ui.navegation

sealed class Destination(val route: String) {
    data object Login : Destination("login")
    data object Home : Destination("home")
}
