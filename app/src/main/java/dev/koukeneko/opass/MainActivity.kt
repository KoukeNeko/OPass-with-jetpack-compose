package dev.koukeneko.opass

import HomeScreen
import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.core.view.WindowCompat
import androidx.core.view.WindowCompat.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.koukeneko.opass.screens.WebViewScreen
import dev.koukeneko.opass.structs.BottomNavItem
import dev.koukeneko.opass.ui.theme.OPassTheme

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //if sdk version > 29
        if (android.os.Build.VERSION.SDK_INT > android.os.Build.VERSION_CODES.R) {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        } else {
            enableEdgeToEdge()
        }

        setContent {
            OPassTheme {
                val lightTheme = !isSystemInDarkTheme()
                val barColor = MaterialTheme.colorScheme.background.toArgb()
                LaunchedEffect(lightTheme) {
                    if (lightTheme) {
                        enableEdgeToEdge(
                            statusBarStyle = SystemBarStyle.light(
                                barColor, barColor,
                            ),
                            navigationBarStyle = SystemBarStyle.light(
                                barColor, barColor,
                            ),
                        )
                    } else {
                        enableEdgeToEdge(
                            statusBarStyle = SystemBarStyle.dark(
                                barColor,
                            ),
                            navigationBarStyle = SystemBarStyle.dark(
                                barColor,
                            ),
                        )
                    }
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppContent()
                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppContent() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("home") {

            HomeScreen(navController = navController)

        }
        composable("web_view") {
            WebViewScreen(
                navController = navController,
                url = "https://sitcon.org/2024"
            ) //TODO make url dynamic
        }
        composable("schedule") {

            Column {
                Button(onClick = { navController.popBackStack() }) {
                    Text("Go to home")
                }
            }

        }


    }

}