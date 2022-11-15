package com.ascri.composebox.presentation.flow.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ascri.composebox.presentation.flow.architecture_example.CounterScreenArgs
import com.ascri.composebox.presentation.flow.architecture_example.navigateToCounter
import com.ascri.composebox.presentation.flow.bottom_menu.navigateToBottomBar
import com.ascri.composebox.presentation.flow.canvas.navigateToCustomDraw
import com.ascri.composebox.presentation.flow.click.navigateToClick
import com.ascri.composebox.presentation.flow.horizontal_list.navigateToHorizontalScroll
import com.ascri.composebox.presentation.flow.list.navigateToList
import com.ascri.composebox.presentation.flow.top_bar.navigateToTopBar

@Composable
fun MainScreen(navController: NavController) {
    val viewList = mutableListOf(
        Router("Counter") { navController.navigateToCounter(CounterScreenArgs(0)) },
        Router("Bottom Bar") { navController.navigateToBottomBar() },
        Router("Custom Draw") { navController.navigateToCustomDraw() },
        Router("Click") { navController.navigateToClick() },
        Router("List") { navController.navigateToList() },
        Router("Top App Bar"){navController.navigateToTopBar()},
        Router("Horizontal Scroll"){navController.navigateToHorizontalScroll()}
    )
    LazyColumn(modifier = Modifier.padding(vertical = 32.dp)) {
        itemsIndexed(viewList) { _, item ->
            MainScreenItem(item = item)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MainScreenItem(item: Router) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        backgroundColor = MaterialTheme.colorScheme.primary,
        shape = RoundedCornerShape(16.dp),
        onClick = {
            item.onNavigate.invoke()
        }
    ) {
        Text(
            text = item.name,
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Center,
            color = Color.White
        )
    }
}

data class Router(val name: String, val onNavigate: () -> Unit)