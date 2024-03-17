import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import dev.koukeneko.opass.R
import dev.koukeneko.opass.components.AppBar
import dev.koukeneko.opass.components.PanelBtn
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
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

    Scaffold(
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
                        showBottomSheet = true
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.rounded_stack_24),
                            tint = MaterialTheme.colorScheme.onSurface,
                            contentDescription = "Localized description",
                        )
                    }
                }
            )
        }
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
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
        var search_event by remember { mutableStateOf("") }
        val events = listOf("SITCON 2024", "DevFest Taipei 2023", "Event 3", "Event 4", "Event 5", "Event 6", "Event 7", "Event 8", "Event 9", "Event 10","Event 11", "Event 12", "Event 13", "Event 14", "Event 15", "Event 16", "Event 17", "Event 18", "Event 19", "Event 20")
        val filteredItems = events.filter { it -> it.contains(search_event, ignoreCase = true) }


        if (showBottomSheet) {
            ModalBottomSheet(

                onDismissRequest = {
                    showBottomSheet = false
                },
                sheetState = sheetState
            ) {

                Column(
                    modifier = Modifier.padding(10.dp, 0.dp, 10.dp, 0.dp),
                ) {

                    LazyColumn {
                        item {
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    TextField(

                                        label = { Row {
                                            Icon(Icons.Rounded.Search, contentDescription = "Localized description")
                                            Text("搜尋活動")
                                        } },
                                        modifier = Modifier.fillParentMaxWidth(),
                                        value = search_event,
                                        onValueChange = {
                                            search_event = it
                                    })
                                    Button(
                                        modifier = Modifier.width(50.dp),
                                        onClick = { /*TODO*/ }) {
//                                search icon
                                        Icon(Icons.Filled.Search, contentDescription = "Localized description")
                                    }
                                }
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                        itemsIndexed(filteredItems) { index, event ->
                            val shape = when (index) {
                                0 -> RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp)
                                events.lastIndex -> RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp)
                                else -> RoundedCornerShape(0.dp)
                            }
                            Card(
                                shape = shape,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 10.dp, end = 10.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { /*TODO*/ }
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                    ) {
                                        Image(imageVector = SITCON_white(), contentDescription = event, modifier = Modifier
                                            .padding(10.dp)
                                            .height(70.dp)
                                            .width(100.dp))
                                        Text(
                                            text = event,
                                        )
                                    }
                                    Icon(imageVector = chevron_right(), contentDescription = event, modifier = Modifier
                                        .padding(end = 10.dp)
                                        .height(30.dp), tint = MaterialTheme.colorScheme.onSurface)
                                }
                            }
                            if (index < events.lastIndex) {
                                Divider(
                                    color = Color.Transparent,
                                    thickness = 1.dp,
                                )
                            }
                        }
                        item {
                            Spacer(modifier = Modifier.height(20.dp))
                        }
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
}

data class PanelButton(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)