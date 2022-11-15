package com.ascri.composebox.presentation.flow.horizontal_list

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.ascri.composebox.R

@Composable
fun HorizontalScrollView(navController: NavController) {
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    Scaffold(scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(title = { Text("Scroll") },
                navigationIcon = {
                    Image(
                        painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24),
                        colorFilter = ColorFilter.tint(Color.White),
                        contentDescription = "",
                        modifier = Modifier
                            .clickable(enabled = true) {
                                navController.popBackStack()
                            }
                            .padding(start = 8.dp, top = 12.dp, bottom = 12.dp)
                    )
                }
            )
        }
    ) { _ ->
        Column {
            HorizontalScroll()
            VerticalScrollView()
        }
    }
}

@Composable
fun HorizontalScroll() {
    val itemList =
        arrayListOf("Apple", "Google", "Microsoft", "Amazon", "Some company", "Else Company")
    LazyRow {
        itemsIndexed(itemList) { _, item ->
            HorizontalScrollItem(item = item)
        }
    }
}

@Composable
fun HorizontalScrollItem(item: String) {
    Card(modifier = Modifier.padding(8.dp), shape = RoundedCornerShape(16.dp), elevation = 8.dp) {
        Text(text = item, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
    }
}

@Composable
fun VerticalScrollView() {
    val itemList =
        arrayListOf("Apple", "Google", "Microsoft", "Amazon", "Some company", "Else Company","Apple", "Google", "Microsoft", "Amazon", "Some company", "Else Company")
    LazyColumn{
        itemsIndexed(itemList) { _, item ->
            HorizontalScrollItem(item = item)
        }
    }
}