package com.ascri.composebox.presentation.flow.bottom_menu

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ascri.composebox.R
import com.ascri.composebox.presentation.MainPreview

@Composable
fun BottomBarScreen(navController: NavController){
    BottomBarScreenBody { navController.popBackStack() }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarScreenBody(onBackClick: () -> Unit) {
    val fabShape = RoundedCornerShape(50)
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Bottom Bar vs Fab") }, navigationIcon = {
                Image(
                    painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                    contentDescription = "",
                    modifier = Modifier
                        .clickable(enabled = true) {
                            onBackClick.invoke()
                        }
                        .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                )
            }
            )
        },
        bottomBar = {
            BottomAppBar(cutoutShape = fabShape) {
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {

                },
                shape = fabShape,
                backgroundColor = MaterialTheme.colors.secondary
            ) {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Add, contentDescription = "Favorite")
                }
            }
        },
        isFloatingActionButtonDocked = true,
        floatingActionButtonPosition = FabPosition.Center,
        content = {

        }
    )
}

@Preview
@Composable
fun BottomBarScreenPopulate() {
    MainPreview { BottomBarScreenBody {} }
}