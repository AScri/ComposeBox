package com.ascri.composebox.presentation.flow.top_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ascri.composebox.R
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TopBar(
    navController: NavController
) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState =  scaffoldState,
        topBar = {
            TopAppBar(title = { Text("Bottom Bar vs Fab") },
                navigationIcon = {
                Image(
                    painterResource(id = R.drawable.ic_baseline_menu_24),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(enabled = true) {
                            //navController.popBackStack()
                            scope.launch {
                                scaffoldState.drawerState.open()
                            }
                        }
                        .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                )
            }
            )
        },
        drawerContent = { DrawerView()}
    ) {
        Column() {
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
        }
    }
}



@Composable
fun DrawerView() {
    val list = arrayListOf("Kotlin", "Java", "C++", "Swift")
    LazyColumn {
        itemsIndexed(list) { _, item ->
            DrawerItem(item = item)
        }
    }
}

@Composable
fun DrawerItem(item: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        border = BorderStroke(1.dp, color = Color.Gray),
    ) {
        Text(
            text = item,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            modifier = Modifier.padding(12.dp)
        )

    }
}