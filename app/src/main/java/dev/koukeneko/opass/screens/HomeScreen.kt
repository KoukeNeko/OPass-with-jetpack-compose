import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.koukeneko.opass.R
import dev.koukeneko.opass.components.AppBar
import dev.koukeneko.opass.components.PanelBtn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {
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
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ) {
            PanelBtn(
                title = "公告",
                icon = painterResource(id =R.drawable.rounded_chat_info_24),
                onClick = { navController.navigate("profile")},
                color = MaterialTheme.colorScheme.primary
            )
            PanelBtn(
                title = "WiFi",
                icon = painterResource(id =R.drawable.rounded_wifi_24),
                onClick = { /*TODO*/ },
                color = MaterialTheme.colorScheme.primary
            )
            PanelBtn(
                title = "會場",
                icon = painterResource(id =R.drawable.rounded_map_24),
                onClick = { /*TODO*/ },
                color = MaterialTheme.colorScheme.primary
            )
            PanelBtn(
                title = "贊助商",
                icon = painterResource(id =R.drawable.rounded_monetization_on_24),
                onClick = { /*TODO*/ },
                color = MaterialTheme.colorScheme.primary
            )

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