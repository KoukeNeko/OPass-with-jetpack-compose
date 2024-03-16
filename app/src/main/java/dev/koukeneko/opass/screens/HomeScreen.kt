import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.koukeneko.opass.R
import dev.koukeneko.opass.components.PanelBtn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
    val buttons = listOf(
        PanelButton("快速通關", ticket()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("議程", calendar_clock()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("我的票券", qrcode()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("大地遊戲", puzzle()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("大會公告", speakerphone()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("WiFi", wifi()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("會場地圖", map()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("合作夥伴", cash()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("工作人員", user_grop()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("個人贊助支持", heart()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("Telegram", telegram()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("Discord", discord()) {
            navController.navigate(
                "profile"
            )
        },
        PanelButton("IRC Log", chat()) {
            navController.navigate(
                "profile"
            )
        },
    )


    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
//                    .background(Color.Red)
        ) {
            Image(
                SITCON_white(),
                contentDescription = "SITCON Logo",
                modifier = Modifier
                    .width(250.dp)
                    .height(200.dp)
                    .align(Alignment.BottomCenter)
//                        .background(Color.Magenta)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        LazyVerticalGrid(
            columns = GridCells.Fixed(4), // Fixed count of 4 items per row
            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(buttons) { button ->
                PanelBtn(
                    title = button.title,
                    icon = button.icon,
                    onClick = button.onClick,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState
        ) {
            Row {
                TextField(value = "dsa", onValueChange = {
                    /*TODO*/
                })
                Button(onClick = { /*TODO*/ }) {
//                                search icon
                    Icon(Icons.Filled.Search, contentDescription = "Localized description")
                }
            }
            Column {
                Card {
                    Text("Sheet content")
                }
                Card {
                    Text("Sheet content")
                }
                Card {
                    Text("Sheet content")
                }
            }
            // Sheet content
            Button(onClick = {
                scope.launch { sheetState.hide() }.invokeOnCompletion {
                    if (!sheetState.isVisible) {
                        showBottomSheet = false
                    }
                }
            }) {
                Text("Hide bottom sheet")
            }
        }
    }
}

data class PanelButton(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)