package dev.koukeneko.opass

import HomeScreen
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.koukeneko.opass.components.AppBar
import dev.koukeneko.opass.components.NavigationBarComponent
import dev.koukeneko.opass.screens.WebViewScreen
import dev.koukeneko.opass.structs.BottomNavItem
import dev.koukeneko.opass.ui.theme.OPassTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

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

    val bottomNavItems = listOf(
        BottomNavItem(
            name = "首頁",
            route = "home",
            icon = { Icon(Icons.Rounded.Home, contentDescription = "Home") }
        ),
        BottomNavItem(
            name = "快速通關",
            route = "fast_pass",
            icon = {
                Icon(
                    painterResource(id = R.drawable.rounded_confirmation_number_24),
                    contentDescription = "Fast Pass"
                )
            }
        ),
        BottomNavItem(
            name = "我的票券",
            route = "ticket",
            icon = {
                Icon(
                    painterResource(id = R.drawable.rounded_qr_code_24),
                    contentDescription = "Ticket"
                )
            }
        ),
        BottomNavItem(
            name = "議程",
            route = "schedule",
            icon = {
                Icon(
                    painterResource(id = R.drawable.rounded_format_list_numbered_24),
                    contentDescription = "Schedule"
                )
            }
        ),

        )


    val navController = rememberNavController()
    // NavController is passed via parameter
    val backStackEntry = navController.currentBackStackEntryAsState()

    Scaffold(
        bottomBar = {
            NavigationBarComponent(
                bottomNavItems = bottomNavItems,
                backStackEntry = backStackEntry,
                navController = navController
            )
        },
        topBar = {
            AppBar(
                subtitle = "KoukeNeko",
                title = "SITCON 2024",
                rightIcon = {
                    IconButton(onClick = { /* Do something */ }) {
                        Icon(Icons.Filled.Settings, contentDescription = "Localized description")
                    }
                },
                leftIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_stack_24),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        },
    ) {
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.fillMaxSize().padding(it)
        ) {
            composable("home") {

                HomeScreen(navController = navController)

            }
            composable("web_view") {
                WebViewScreen(navController = navController, url = "https://sitcon.org/2024") //TODO make url dynamic
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

}